/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.netbeans.modules.subversion.ui.commit;

import java.awt.Color;
import org.netbeans.modules.versioning.util.FilePathCellRenderer;
import org.netbeans.modules.versioning.util.TableSorter;
import org.netbeans.modules.subversion.util.SvnUtils;
import org.netbeans.modules.subversion.SvnFileNode;
import org.netbeans.modules.subversion.Subversion;
import org.openide.util.NbBundle;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableColumnModel;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.*;
import javax.swing.table.TableCellRenderer;
import org.netbeans.modules.subversion.FileInformation;
import org.netbeans.modules.versioning.util.SortedTable;
import org.openide.awt.Mnemonics;

/**
 * {@link #getComponent Table} that displays nodes in the commit dialog.
 * 
 * @author Maros Sandor
 */
public class CommitTable implements AncestorListener, TableModelListener, MouseListener {

    public static String [] COMMIT_COLUMNS = new String [] {
                                            CommitTableModel.COLUMN_NAME_COMMIT,
                                            CommitTableModel.COLUMN_NAME_NAME,
                                            CommitTableModel.COLUMN_NAME_STATUS,
                                            CommitTableModel.COLUMN_NAME_ACTION,
                                            CommitTableModel.COLUMN_NAME_PATH
                                        };

    public static String [] IMPORT_COLUMNS = new String [] {
                                            CommitTableModel.COLUMN_NAME_COMMIT,
                                            CommitTableModel.COLUMN_NAME_NAME,
                                            CommitTableModel.COLUMN_NAME_ACTION,
                                            CommitTableModel.COLUMN_NAME_PATH
                                        };
    
    private CommitTableModel    tableModel;
    private JTable              table;
    private JComponent          component;
    
    private TableSorter         sorter;
    private String[]            columns;
    private Map<String, Integer>            sortByColumns;
    private CommitPanel commitPanel;
    private Set<File> modifiedFiles = Collections.<File>emptySet();
    
    
    public CommitTable(JLabel label, String[] columns, Map<String, Integer> sortByColumns) {
        init(label, columns, null);
        this.sortByColumns = sortByColumns;        
        setSortingStatus();            
    }

    public CommitTable(JLabel label, String[] columns, TableSorter sorter) {
        init(label, columns, sorter);        
    }
    
    private void init(JLabel label, String[] columns, TableSorter sorter) {
        tableModel = new CommitTableModel(columns);
        tableModel.addTableModelListener(this);
        if(sorter == null) {
            sorter = new TableSorter(tableModel);
        } 
        this.sorter = sorter;   
        table = new SortedTable(this.sorter);
        table.getTableHeader().setReorderingAllowed(false);
        table.setDefaultRenderer(String.class, new CommitStringsCellRenderer());
        table.setDefaultRenderer(Boolean.class, new CheckboxCellRenderer());
        table.setDefaultEditor(Boolean.class, new CheckboxCellEditor());
        table.getTableHeader().setReorderingAllowed(true);
        table.setRowHeight(table.getRowHeight() * 6 / 5);
        table.addAncestorListener(this);
        component = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        label.setLabelFor(table);
        table.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(CommitTable.class, "ACSD_CommitTable")); // NOI18N
        table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_F10, KeyEvent.SHIFT_DOWN_MASK ), "org.openide.actions.PopupAction"); // NOI18N
        table.getActionMap().put("org.openide.actions.PopupAction", new AbstractAction() { // NOI18N
            @Override
            public void actionPerformed(ActionEvent e) {
                showPopup(org.netbeans.modules.versioning.util.Utils.getPositionForPopup(table));
            }
        });
        table.addMouseListener(this);
        setColumns(columns);
    }

    @Override
    public void ancestorAdded(AncestorEvent event) {
        setDefaultColumnSizes();
    }

    /**
     * Sets sizes of Commit table columns, kind of hardcoded.
     */ 
    private void setDefaultColumnSizes() {
        int width = table.getWidth();
        TableColumnModel columnModel = table.getColumnModel();
        if (columns == null || columnModel == null) return; // unsure when this methed will be called (component realization) 
        if (columnModel.getColumnCount() != columns.length) return; 
        if (columns.length == 4) {
            for (int i = 0; i < columns.length; i++) {
                String col = columns[i];                                
                sorter.setColumnComparator(i, null);                    
                if (col.equals(CommitTableModel.COLUMN_NAME_COMMIT)) {
                    columnModel.getColumn(i).setMinWidth(new JCheckBox().getMinimumSize().width);
                    columnModel.getColumn(i).setPreferredWidth(new JCheckBox().getPreferredSize().width);
                } else if (col.equals(CommitTableModel.COLUMN_NAME_NAME)) {
                    sorter.setColumnComparator(i, new FileNameComparator());
                    columnModel.getColumn(i).setPreferredWidth(width * 30 / 100);
                } else if (col.equals(CommitTableModel.COLUMN_NAME_ACTION)) {
                    columnModel.getColumn(i).setPreferredWidth(width * 15 / 100);
                } else {
                    columnModel.getColumn(i).setPreferredWidth(width * 40 / 100);
                }                
            }
        } else if (columns.length == 5) {
            for (int i = 0; i < columns.length; i++) {
                String col = columns[i];                                
                sorter.setColumnComparator(i, null);                    
                if (col.equals(CommitTableModel.COLUMN_NAME_COMMIT)) {
                    columnModel.getColumn(i).setMinWidth(new JCheckBox().getMinimumSize().width);
                    columnModel.getColumn(i).setPreferredWidth(new JCheckBox().getPreferredSize().width);
                } else if (col.equals(CommitTableModel.COLUMN_NAME_NAME)) {
                    sorter.setColumnComparator(i, new FileNameComparator());
                    columnModel.getColumn(i).setPreferredWidth(width * 30 / 100);
                } else if (col.equals(CommitTableModel.COLUMN_NAME_STATUS)) {
                    sorter.setColumnComparator(i, new StatusComparator());                    
                    columnModel.getColumn(i).setPreferredWidth(width * 15 / 100);
                } else if (col.equals(CommitTableModel.COLUMN_NAME_ACTION)) {
                    columnModel.getColumn(i).setPreferredWidth(width * 15 / 100);
                } else {
                    columnModel.getColumn(i).setPreferredWidth(width * 40 / 100);
                }                
            }
        } else if (columns.length == 6) {
            for (int i = 0; i < columns.length; i++) {
                String col = columns[i];
                sorter.setColumnComparator(i, null);                
                if (col.equals(CommitTableModel.COLUMN_NAME_COMMIT)) {
                    columnModel.getColumn(i).setMinWidth(new JCheckBox().getMinimumSize().width);
                    columnModel.getColumn(i).setPreferredWidth(new JCheckBox().getPreferredSize().width);
                } else if (col.equals(CommitTableModel.COLUMN_NAME_NAME)) {
                    sorter.setColumnComparator(i, new FileNameComparator());
                    columnModel.getColumn(i).setPreferredWidth(width * 25 / 100);
                } else if (col.equals(CommitTableModel.COLUMN_NAME_STATUS)) {
                    sorter.setColumnComparator(i, new StatusComparator());
                    sorter.setSortingStatus(i, TableSorter.ASCENDING);
                    columnModel.getColumn(i).setPreferredWidth(width * 15 / 100);
                } else if (col.equals(CommitTableModel.COLUMN_NAME_ACTION)) {
                    columnModel.getColumn(i).setPreferredWidth(width * 15 / 100);
                } else {
                    columnModel.getColumn(i).setPreferredWidth(width * 30 / 100);
                }
            }
        }
    }

    private void setSortingStatus() {
        for (Map.Entry<String, Integer> e : sortByColumns.entrySet()) {
            String sortByColumn = e.getKey();
            for (int j = 0; j < columns.length; j++) {
                String column = columns[j];
                if(column.equals(sortByColumn)) {
                    sorter.setSortingStatus(j, e.getValue());
                    break;
                }                    
            }                        
        }        
    }

    public LinkedHashMap<String, Integer> getSortingState() {
        Map<Integer, Integer> sorterState = sorter.getSortingState();
        LinkedHashMap<String, Integer> sortingStatus = new LinkedHashMap<String, Integer>(sorterState.size());
        for (Map.Entry<Integer, Integer> e : sorterState.entrySet()) {
            sortingStatus.put(columns[e.getKey()], e.getValue());
        }
        return sortingStatus;
    }
    
    @Override
    public void ancestorMoved(AncestorEvent event) {
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
    }
    
    void setColumns(String[] cols) {
        if (Arrays.equals(columns, cols)) return;
        columns = cols;
        tableModel.setColumns(cols);
        setDefaultColumnSizes();
    }

    /**
     * Note that each node should have it's values initialized
     * @param nodes
     */
    public void setNodes(SvnFileNode[] nodes) {
        tableModel.setNodes(nodes);
    }

    /**
     * @return Map&lt;SvnFileNode, CommitOptions>
     */
    public Map<SvnFileNode, CommitOptions> getCommitFiles() {
        return tableModel.getCommitFiles();
    }

    /**
     * @return table in a scrollpane 
     */
    public JComponent getComponent() {
        return component;
    }

    void dataChanged() {
        int idx = table.getSelectedRow();
        tableModel.fireTableDataChanged();
        if (idx != -1) table.getSelectionModel().addSelectionInterval(idx, idx);
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        // change in commit options may alter name rendering (strikethrough)
        table.repaint();
    }

    public void setRootFile(String repositoryPath, String rootLocalPath) {
        tableModel.setRootFile(repositoryPath, rootLocalPath);
    }
    
    private void showPopup (final MouseEvent e) {
        int row = table.rowAtPoint(e.getPoint());
        int col = table.columnAtPoint(e.getPoint());
        if (row != -1) {
            boolean makeRowSelected = true;
            int [] selectedrows = table.getSelectedRows();
            for (int i = 0; i < selectedrows.length; i++) {
                if (row == selectedrows[i]) {
                    makeRowSelected = false;
                    break;
                }
            }
            if (makeRowSelected) {
                table.getSelectionModel().setSelectionInterval(row, row);
            }
        }
        if (col != -1) {
            boolean makeColSelected = true;
            int [] selectedcols = table.getSelectedColumns();
            for (int i = 0; i < selectedcols.length; i++) {
                if (col == selectedcols[i]) {
                    makeColSelected = false;
                    break;
                }
            }
            if (makeColSelected) {
                table.getColumnModel().getSelectionModel().setSelectionInterval(col, col);
            }
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // invoke later so the selection on the table will be set first
                if (table.isShowing()) {
                    JPopupMenu menu = getPopup();
                    menu.show(table, e.getX(), e.getY());
                }
            }
        });
    }

    private void showPopup (Point p) {
        JPopupMenu menu = getPopup();
        menu.show(table, p.x, p.y);
    }

    private JPopupMenu getPopup() {

        JPopupMenu menu = new JPopupMenu();
        JMenuItem item;
        boolean containsExcluded = false;
        boolean containsIncluded = false;
        boolean anyDirectory = false;
        boolean addAllowed = true;
        for (int rowIndex : table.getSelectedRows()) {
            int row = sorter.modelIndex(rowIndex);
            SvnFileNode node = tableModel.getNode(row);
            FileInformation fileInfo = node.getInformation();
            if (CommitOptions.EXCLUDE.equals(tableModel.getOptions(row))) {
                containsExcluded = true;
            } else {
                containsIncluded = true;
            }
            if (fileInfo.isDirectory()) {
                anyDirectory = true;
            }
            if (!node.isFile() || (node.getInformation().getStatus() & FileInformation.STATUS_NOTVERSIONED_NEWLOCALLY) == 0) {
                addAllowed = false;
            }
        }
        if (containsExcluded) {
            item = menu.add(new PopupAction(NbBundle.getMessage(CommitTable.class, "CTL_CommitTable_IncludeAction")) { // NOI18N
                @Override
                public void performAction (ActionEvent e) {
                    int[] rows = getRows();
                    tableModel.setIncluded(rows, true, false);
                }
            });
            Mnemonics.setLocalizedText(item, item.getText());
            item = menu.add(new PopupAction(NbBundle.getMessage(CommitTable.class, "CTL_CommitTable_IncludeRecursivelyAction")) { // NOI18N
                @Override
                public void performAction (ActionEvent e) {
                    int[] rows = getRows();
                    tableModel.setIncluded(rows, true, true);
                }
            });
            Mnemonics.setLocalizedText(item, item.getText());
            item.setEnabled(anyDirectory);
        }
        if (containsIncluded) {
            item = menu.add(new PopupAction(NbBundle.getMessage(CommitTable.class, "CTL_CommitTable_ExcludeAction")) { // NOI18N
                @Override
                public void performAction (ActionEvent e) {
                    int[] rows = getRows();
                    tableModel.setIncluded(rows, false, false);
                }
            });
            Mnemonics.setLocalizedText(item, item.getText());
            item = menu.add(new PopupAction(NbBundle.getMessage(CommitTable.class, "CTL_CommitTable_ExcludeRecursivelyAction")) { // NOI18N
                @Override
                public void performAction (ActionEvent e) {
                    int[] rows = getRows();
                    tableModel.setIncluded(rows, false, true);
                }
            });
            Mnemonics.setLocalizedText(item, item.getText());
            item.setEnabled(anyDirectory);
        }
        item = menu.add(new PopupAction(NbBundle.getMessage(CommitTable.class, "CTL_CommitTable_AddTextAction")) { // NOI18N
            @Override
            public void performAction (ActionEvent e) {
                int[] rows = getRows();
                tableModel.setAdded(rows, CommitOptions.ADD_TEXT);
            }
        });
        Mnemonics.setLocalizedText(item, item.getText());
        item.setEnabled(addAllowed);
        item = menu.add(new PopupAction(NbBundle.getMessage(CommitTable.class, "CTL_CommitTable_AddBinaryAction")) { // NOI18N
            @Override
            public void performAction (ActionEvent e) {
                int[] rows = getRows();
                tableModel.setAdded(rows, CommitOptions.ADD_BINARY);
            }
        });
        Mnemonics.setLocalizedText(item, item.getText());
        item.setEnabled(addAllowed);
        item = menu.add(new AbstractAction(NbBundle.getMessage(CommitTable.class, "CTL_CommitTable_DiffAction")) { // NOI18N
            @Override
            public void actionPerformed(ActionEvent e) {
                openDiff();
            }
        });
        Mnemonics.setLocalizedText(item, item.getText());
        item.setEnabled(commitPanel != null);
        return menu;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            showPopup(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            showPopup(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && commitPanel != null) {
            openDiff();
        }
    }

    private void openDiff () {
        int[] rows = table.getSelectedRows();
        SvnFileNode[] nodes = new SvnFileNode[rows.length];
        for (int i = 0; i < rows.length; ++i) {
            nodes[i] = tableModel.getNode(sorter.modelIndex(rows[i]));
        }
        commitPanel.openDiff(nodes);
    }

    /**
     * This action keeps selection of rows in the table
     */
    private abstract class PopupAction extends AbstractAction {

        private int[] rows;

        public PopupAction (String name) {
            super(name);
        }

        @Override
        public final void actionPerformed(ActionEvent e) {
            rows = table.getSelectedRows();
            int rowCount = table.getRowCount();
            for (int i = 0; i < rows.length; ++i) {
                rows[i] = sorter.modelIndex(rows[i]);
            }
            performAction(e);
            if (rowCount == table.getRowCount()) {
                for (int i = 0; i < rows.length; ++i) {
                    table.getSelectionModel().addSelectionInterval(sorter.viewIndex(rows[i]), sorter.viewIndex(rows[i]));
                }
            }
        }

        protected int[] getRows () {
            return rows;
        }

        protected abstract void performAction (ActionEvent e);
    }

    void setCommitPanel(CommitPanel panel) {
        this.commitPanel = panel;
    }

    void setModifiedFiles(Set<File> modifiedFiles) {
        this.modifiedFiles = modifiedFiles;
    }

    private class CommitStringsCellRenderer extends DefaultTableCellRenderer {

        private FilePathCellRenderer pathRenderer = new FilePathCellRenderer();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            int col = table.convertColumnIndexToModel(column);
            if (CommitTableModel.COLUMN_NAME_NAME.equals(columns[col])) {
                TableSorter sorter = (TableSorter) table.getModel();
                CommitTableModel model = (CommitTableModel) sorter.getTableModel();
                SvnFileNode node = model.getNode(sorter.modelIndex(row));
                CommitOptions options = model.getOptions(sorter.modelIndex(row));
                if (!isSelected) {
                    value = Subversion.getInstance().getAnnotator().annotateNameHtml(node.getFile().getName(), node.getInformation(), null);
                }
                if (options == CommitOptions.EXCLUDE) {
                    value = "<s>" + value + "</s>"; // NOI18N
                }
                if (modifiedFiles.contains(node.getFile())) {
                    value = "<strong>" + value + "</strong>"; //NOI18N
                }
                value = "<html>" + value + "</html>"; //NOI18N
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            } else if (CommitTableModel.COLUMN_NAME_PATH.equals(columns[col])) {
                return pathRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            } else {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        }
    }
    
    private class CheckboxCellRenderer extends JCheckBox implements TableCellRenderer {
        private final DefaultTableCellRenderer renderer;

        public CheckboxCellRenderer() {
            renderer = new DefaultTableCellRenderer();
            setToolTipText(NbBundle.getMessage(CommitTable.class, "CTL_CommitTable_Column_Description")); //NOI18N
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setSelected(value == null ? false : (Boolean) value);
            Color c = renderer.getTableCellRendererComponent(table, "value", isSelected, hasFocus, row, column).getBackground();
            setBackground(new Color(c.getRGB()));
            setOpaque(true);
            setHorizontalAlignment(SwingConstants.LEFT);
            return this;
        }
    }

    private class CheckboxCellEditor extends DefaultCellEditor {

        public CheckboxCellEditor() {
            super(new JCheckBox());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JCheckBox checkbox = (JCheckBox) editorComponent;
            checkbox.setSelected(value == null ? false : (Boolean) value);
            checkbox.setHorizontalAlignment(SwingConstants.LEFT);
            return super.getTableCellEditorComponent(table, value, isSelected, row, column);
        }
    }

    private class StatusComparator extends SvnUtils.ByImportanceComparator {
        public int compare(Object o1, Object o2) {
            Integer row1 = (Integer) o1;
            Integer row2 = (Integer) o2;
            return super.compare(tableModel.getNode(row1.intValue()).getInformation(),
                                 tableModel.getNode(row2.intValue()).getInformation());
        }
    }
    
    private class FileNameComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Integer row1 = (Integer) o1;
            Integer row2 = (Integer) o2;
            return tableModel.getNode(row1.intValue()).getName().compareToIgnoreCase(
                    tableModel.getNode(row2.intValue()).getName());
        }
    }


}
