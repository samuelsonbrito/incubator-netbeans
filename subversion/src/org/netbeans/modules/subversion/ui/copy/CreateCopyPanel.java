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

package org.netbeans.modules.subversion.ui.copy;

import org.tigris.subversion.svnclientadapter.SVNRevision;

/**
 *
 * @author  Petr Kuzel
 */
public class CreateCopyPanel extends javax.swing.JPanel {

    /** Creates new form WorkdirPanel */
    public CreateCopyPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setName(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "CTL_CopyForm_Name")); // NOI18N

        jLabel1.setLabelFor(messageTextArea);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "CTL_CopyForm_Description")); // NOI18N

        messageTextArea.setColumns(20);
        messageTextArea.setRows(5);
        jScrollPane1.setViewportView(messageTextArea);
        messageTextArea.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSN_CopyForm_Description")); // NOI18N
        messageTextArea.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSD_CopyForm_Description")); // NOI18N

        copyToLabel.setLabelFor(urlComboBox);
        org.openide.awt.Mnemonics.setLocalizedText(copyToLabel, org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "CTL_CopyForm_toFolder")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(browseRepositoryButton, org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "CTL_CopyForm_Browse")); // NOI18N

        urlComboBox.setEditable(true);

        warningLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/netbeans/modules/subversion/resources/icons/warning.gif"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(warningLabel, org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "CTL_CopyForm_Warning")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(switchToCheckBox, org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "CTL_CopyForm_Switch")); // NOI18N
        switchToCheckBox.setToolTipText(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSD_SwitchToCopy")); // NOI18N
        switchToCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

        copyFromLocalTextField.setEditable(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "CTL_CopyForm_Source")); // NOI18N

        buttonGroup1.add(localRadioButton);
        localRadioButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(localRadioButton, org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "CTL_CopyForm_fromLocalFolder")); // NOI18N
        localRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

        copyFromRemoteTextField.setEditable(false);

        buttonGroup1.add(remoteRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(remoteRadioButton, org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "CTL_CopyForm_fromRemoteFolder")); // NOI18N
        remoteRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel4.setLabelFor(copyFromRevisionTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "CTL_Revision")); // NOI18N

        copyFromRevisionTextField.setText(SVNRevision.HEAD.toString());
        copyFromRevisionTextField.setToolTipText(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "LBL_CopyForm_RevisionHint")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(searchButton, org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "CTL_Search")); // NOI18N
        searchButton.setToolTipText(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSD_Search")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "CTL_CopyForm_Destination")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(skipCheckBox, org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "CTL_Skip")); // NOI18N
        skipCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel7.setLabelFor(previewTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "CTL_CopyForm_Preview")); // NOI18N

        previewTextField.setEditable(false);

        invalidValuesLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/netbeans/modules/subversion/resources/icons/info.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(invalidValuesLabel, "Error");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(remoteRadioButton)
                            .addComponent(localRadioButton)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(copyFromRemoteLabel))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(copyFromRevisionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton))
                            .addComponent(copyFromLocalTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                            .addComponent(copyFromRemoteTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)))
                    .addComponent(skipCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addComponent(switchToCheckBox)
                    .addComponent(invalidValuesLabel)
                    .addComponent(warningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(copyToLabel)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(previewTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(urlComboBox, 0, 573, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseRepositoryButton)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(localRadioButton)
                    .addComponent(copyFromLocalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(copyFromRemoteLabel))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(remoteRadioButton)
                        .addComponent(copyFromRemoteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(copyFromRevisionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(skipCheckBox)
                .addGap(31, 31, 31)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(copyToLabel)
                    .addComponent(browseRepositoryButton)
                    .addComponent(urlComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(previewTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(switchToCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(invalidValuesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warningLabel)
                .addContainerGap())
        );

        jLabel1.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSN_CopyForm_Description")); // NOI18N
        jLabel1.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSD_CopyForm_Description")); // NOI18N
        browseRepositoryButton.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSD_Browse_Repository")); // NOI18N
        switchToCheckBox.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSD_SwitchToCopy")); // NOI18N
        copyFromLocalTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSN_CopyForm_fromLocalPath_Preview")); // NOI18N
        copyFromLocalTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSD_CopyForm_fromLocalPath_Preview")); // NOI18N
        localRadioButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSN_LocalFolder")); // NOI18N
        localRadioButton.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSD_LocalFolder")); // NOI18N
        copyFromRemoteTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSN_CopyForm_fromRemotePath_Preview")); // NOI18N
        copyFromRemoteTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSD_CopyForm_fromRemotePath_Preview")); // NOI18N
        remoteRadioButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSN_RemoteFolder")); // NOI18N
        remoteRadioButton.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSD_RemoteFolder")); // NOI18N
        copyFromRevisionTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSN_Revision")); // NOI18N
        copyFromRevisionTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSD_Revision")); // NOI18N
        searchButton.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSN_Search")); // NOI18N
        searchButton.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSD_Search")); // NOI18N
        skipCheckBox.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSN_Skip")); // NOI18N
        skipCheckBox.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSD_Skip")); // NOI18N
        previewTextField.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSN_CopyForm_Preview")); // NOI18N
        previewTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(CreateCopyPanel.class, "ACSD_CopyForm_Preview")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    final javax.swing.JButton browseRepositoryButton = new javax.swing.JButton();
    private javax.swing.ButtonGroup buttonGroup1;
    final javax.swing.JTextField copyFromLocalTextField = new javax.swing.JTextField();
    final javax.swing.JLabel copyFromRemoteLabel = new javax.swing.JLabel();
    final javax.swing.JTextField copyFromRemoteTextField = new javax.swing.JTextField();
    final javax.swing.JTextField copyFromRevisionTextField = new javax.swing.JTextField();
    final javax.swing.JLabel copyToLabel = new javax.swing.JLabel();
    final javax.swing.JLabel invalidValuesLabel = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    final javax.swing.JRadioButton localRadioButton = new javax.swing.JRadioButton();
    final javax.swing.JTextArea messageTextArea = new javax.swing.JTextArea();
    final javax.swing.JTextField previewTextField = new javax.swing.JTextField();
    final javax.swing.JRadioButton remoteRadioButton = new javax.swing.JRadioButton();
    final javax.swing.JButton searchButton = new javax.swing.JButton();
    final javax.swing.JCheckBox skipCheckBox = new javax.swing.JCheckBox();
    final javax.swing.JCheckBox switchToCheckBox = new javax.swing.JCheckBox();
    final javax.swing.JComboBox urlComboBox = new javax.swing.JComboBox();
    final javax.swing.JLabel warningLabel = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
    
}
