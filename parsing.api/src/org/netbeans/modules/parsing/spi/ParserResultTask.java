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

package org.netbeans.modules.parsing.spi;



/**
 * SchedulerTask that process result of parsing. 
 * {@link org.netbeans.modules.parsing.spi.Parser.Result} is created when 
 * parser is finished.
 *
 * @author Jan Jancura
 */
public abstract class ParserResultTask<T extends Parser.Result> extends SchedulerTask {

    /**
     * Called when parser is finished.
     * 
     * @param result        A result of parsing.
     */
    public abstract void run (T result, SchedulerEvent event);

    /**
     * A priority. Less number wins.
     * 
     * @return              Priority of this listener.
     */
    public abstract int getPriority ();
}




