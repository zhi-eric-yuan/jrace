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

package org.apache.commons.math.optimization;

import org.apache.commons.math.analysis.MultivariateFunction;

/**
 * This interface represents an optimization algorithm for {@link MultivariateFunction
 * scalar objective functions}.
 * <p>Optimization algorithms find the input point set that either {@link GoalType
 * maximize or minimize} an objective function.</p>
 *
 * @see DifferentiableMultivariateRealOptimizer
 * @see DifferentiableMultivariateVectorialOptimizer
 * @version $Id: MultivariateRealOptimizer.java 1206889 2011-11-27 22:39:54Z erans $
 * @since 2.0
 */
public interface MultivariateRealOptimizer
    extends BaseMultivariateRealOptimizer<MultivariateFunction> {}