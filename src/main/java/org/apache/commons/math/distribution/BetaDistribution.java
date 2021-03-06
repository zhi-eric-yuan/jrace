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
package org.apache.commons.math.distribution;

import org.apache.commons.math.exception.NumberIsTooSmallException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.special.Gamma;
import org.apache.commons.math.special.Beta;
import org.apache.commons.math.util.FastMath;

/**
 * Implements the Beta distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Beta_distribution">Beta distribution</a>
 * @version $Id: BetaDistribution.java 1205739 2011-11-24 07:07:32Z celestin $
 * @since 2.0 (changed to concrete class in 3.0)
 */
public class BetaDistribution extends AbstractContinuousDistribution {
    /**
     * Default inverse cumulative probability accuracy.
     * @since 2.1
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;
    /** Serializable version identifier. */
    private static final long serialVersionUID = -1221965979403477668L;
    /** First shape parameter. */
    private final double alpha;
    /** Second shape parameter. */
    private final double beta;
    /** Normalizing factor used in density computations.
     * updated whenever alpha or beta are changed.
     */
    private double z;
    /** Inverse cumulative probability accuracy. */
    private final double solverAbsoluteAccuracy;

    /**
     * Build a new instance.
     *
     * @param alpha First shape parameter (must be positive).
     * @param beta Second shape parameter (must be positive).
     * @param inverseCumAccuracy Maximum absolute error in inverse
     * cumulative probability estimates (defaults to
     * {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @since 2.1
     */
    public BetaDistribution(double alpha, double beta, double inverseCumAccuracy) {
        this.alpha = alpha;
        this.beta = beta;
        z = Double.NaN;
        solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    /**
     * Build a new instance.
     *
     * @param alpha First shape parameter (must be positive).
     * @param beta Second shape parameter (must be positive).
     */
    public BetaDistribution(double alpha, double beta) {
        this(alpha, beta, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Access the first shape parameter, {@code alpha}.
     *
     * @return the first shape parameter.
     */
    public double getAlpha() {
        return alpha;
    }

    /**
     * Access the second shape parameter, {@code beta}.
     *
     * @return the second shape parameter.
     */
    public double getBeta() {
        return beta;
    }

    /** Recompute the normalization factor. */
    private void recomputeZ() {
        if (Double.isNaN(z)) {
            z = Gamma.logGamma(alpha) + Gamma.logGamma(beta) - Gamma.logGamma(alpha + beta);
        }
    }

    /** {@inheritDoc} */
    public double density(double x) {
        recomputeZ();
        if (x < 0 || x > 1) {
            return 0;
        } else if (x == 0) {
            if (alpha < 1) {
                throw new NumberIsTooSmallException(LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_0_FOR_SOME_ALPHA, alpha, 1, false);
            }
            return 0;
        } else if (x == 1) {
            if (beta < 1) {
                throw new NumberIsTooSmallException(LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_1_FOR_SOME_BETA, beta, 1, false);
            }
            return 0;
        } else {
            double logX = FastMath.log(x);
            double log1mX = FastMath.log1p(-x);
            return FastMath.exp((alpha - 1) * logX + (beta - 1) * log1mX - z);
        }
    }

    /** {@inheritDoc} */
    @Override
    public double inverseCumulativeProbability(double p)  {
        if (p == 0) {
            return 0;
        } else if (p == 1) {
            return 1;
        } else {
            return super.inverseCumulativeProbability(p);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected double getInitialDomain(double p) {
        return p;
    }

    /** {@inheritDoc} */
    @Override
    protected double getDomainLowerBound(double p) {
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    protected double getDomainUpperBound(double p) {
        return 1;
    }

    /** {@inheritDoc} */
    public double cumulativeProbability(double x)  {
        if (x <= 0) {
            return 0;
        } else if (x >= 1) {
            return 1;
        } else {
            return Beta.regularizedBeta(x, alpha, beta);
        }
    }

    /**
     * Return the absolute accuracy setting of the solver used to estimate
     * inverse cumulative probabilities.
     *
     * @return the solver absolute accuracy.
     * @since 2.1
     */
    @Override
    protected double getSolverAbsoluteAccuracy() {
        return solverAbsoluteAccuracy;
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always 0 no matter the parameters.
     *
     * @return lower bound of the support (always 0)
     */
    @Override
    public double getSupportLowerBound() {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is always 1 no matter the parameters.
     *
     * @return upper bound of the support (always 1)
     */
    @Override
    public double getSupportUpperBound() {
        return 1;
    }

    /**
     * {@inheritDoc}
     *
     * For first shape parameter {@code alpha} and second shape parameter {@code beta}, the
     * mean is {@code alpha / (alpha + beta)}.
     */
    @Override
    protected double calculateNumericalMean() {
        final double a = getAlpha();
        return a / (a + getBeta());
    }

    /**
     * {@inheritDoc}
     *
     * For first shape parameter {@code alpha} and second shape parameter
     * {@code beta}, the variance is
     * {@code (alpha * beta) / [(alpha + beta)^2 * (alpha + beta + 1)]}.
     */
    @Override
    protected double calculateNumericalVariance() {
        final double a = getAlpha();
        final double b = getBeta();
        final double alphabetasum = a + b;
        return (a * b) / ((alphabetasum * alphabetasum) * (alphabetasum + 1));
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSupportLowerBoundInclusive() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSupportUpperBoundInclusive() {
        return false;
    }
}
