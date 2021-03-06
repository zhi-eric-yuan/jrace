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

package org.apache.commons.math.optimization.general;

import org.apache.commons.math.analysis.DifferentiableMultivariateFunction;
import org.apache.commons.math.analysis.MultivariateVectorFunction;
import org.apache.commons.math.optimization.DifferentiableMultivariateRealOptimizer;
import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.ConvergenceChecker;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.direct.BaseAbstractScalarOptimizer;

/**
 * Base class for implementing optimizers for multivariate scalar
 * differentiable functions.
 * It contains boiler-plate code for dealing with gradient evaluation.
 *
 * @version $Id: AbstractScalarDifferentiableOptimizer.java 1207671 2011-11-28 22:41:41Z erans $
 * @since 2.0
 */
public abstract class AbstractScalarDifferentiableOptimizer
    extends BaseAbstractScalarOptimizer<DifferentiableMultivariateFunction>
    implements DifferentiableMultivariateRealOptimizer {
    /**
     * Objective function gradient.
     */
    private MultivariateVectorFunction gradient;

    /**
     * Simple constructor with default settings.
     * The convergence check is set to a
     * {@link org.apache.commons.math.optimization.SimpleScalarValueChecker
     * SimpleScalarValueChecker}.
     */
    protected AbstractScalarDifferentiableOptimizer() {}
    /**
     * @param checker Convergence checker.
     */
    protected AbstractScalarDifferentiableOptimizer(ConvergenceChecker<RealPointValuePair> checker) {
        super(checker);
    }

    /**
     * Compute the gradient vector.
     *
     * @param evaluationPoint Point at which the gradient must be evaluated.
     * @return the gradient at the specified point.
     * @throws org.apache.commons.math.exception.TooManyEvaluationsException
     * if the allowed number of evaluations is exceeded.
     */
    protected double[] computeObjectiveGradient(final double[] evaluationPoint) {
        return gradient.value(evaluationPoint);
    }

    /** {@inheritDoc} */
    @Override
    public RealPointValuePair optimize(int maxEval,
                                       final DifferentiableMultivariateFunction f,
                                       final GoalType goalType,
                                       final double[] startPoint) {
        // Store optimization problem characteristics.
        gradient = f.gradient();

        return super.optimize(maxEval, f, goalType, startPoint);
    }
}
