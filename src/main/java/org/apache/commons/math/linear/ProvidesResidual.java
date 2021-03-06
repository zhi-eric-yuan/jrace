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
package org.apache.commons.math.linear;

/**
 * This interface provides access to the current value of the residual of an
 * {@link IterativeLinearSolver}. It might be implemented by
 * {@link IterativeLinearSolverEvent}, for example.
 *
 * @version $Id: ProvidesResidual.java 1175404 2011-09-25 14:48:18Z celestin $
 * @since 3.0
 */
public interface ProvidesResidual {
    /**
     * Returns the current value of the residual. This should be an
     * unmodifiable view or a deep copy of the residual, in order not to
     * compromise the subsequent iterations.
     *
     * @return the current value of the residual.
     */
    RealVector getResidual();
}
