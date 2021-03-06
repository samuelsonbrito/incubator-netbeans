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
/*
 * GenRunsData.java
 *
 * Created on October 9, 2002, 11:24 PM
 */

package org.netbeans.performance.impl.analysis;
import java.util.*;
import org.netbeans.performance.spi.*;
import org.netbeans.performance.impl.logparsing.*;
import java.io.*;
import org.apache.tools.ant.*;
import org.apache.tools.ant.types.*;
/** Ant task to gather data from the logfiles produced by a run,
 * as pointed to in the log file generated from the initial log file
 * the ant task generates (which just contains start and end times
 * and pointers to log files generated by the IDE's activity).
 *
 * @author  Tim Boudreau
 */
public class GenRunsData extends Analyzer {

    public String analyze() throws BuildException {
        NbRunLog log = new NbRunLog (datafile);
        try {
            log.writeToFile (outfile);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new BuildException ("Exception while creating run set data.", ioe);
        }
        return outfile;
    }

}
