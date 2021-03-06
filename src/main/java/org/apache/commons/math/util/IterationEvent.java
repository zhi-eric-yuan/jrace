/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.math.util;

import java.util.EventObject;

/**
 * The root class from which all events occurring while running an
 * {@link IterationManager} should be derived.
 *
 * @version $Id: IterationEvent.java 1170462 2011-09-14 07:44:00Z celestin $
 */
public class IterationEvent extends EventObject {
    /** */
    private static final long serialVersionUID = -1405936936084001482L;

    /**
     * Creates a new instance of this class.
     *
     * @param source The iterative algorithm on which the event initially
     * occurred.
     */
    public IterationEvent(final Object source) {
        super(source);
    }
}
