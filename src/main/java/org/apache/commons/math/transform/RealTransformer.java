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
package org.apache.commons.math.transform;

import org.apache.commons.math.analysis.UnivariateFunction;

/**
 * Interface for one-dimensional data sets transformations producing real results.
 * <p>Such transforms include {@link FastSineTransformer sine transform},
 * {@link FastCosineTransformer cosine transform} or {@link
 * FastHadamardTransformer Hadamard transform}. {@link FastFourierTransformer
 * Fourier transform} is of a different kind and does not implement this
 * interface since it produces {@link org.apache.commons.math.complex.Complex complex}
 * results instead of real ones.
 * </p>
 * @version $Id: RealTransformer.java 1208293 2011-11-30 06:33:57Z celestin $
 * @since 2.0
 */
public interface RealTransformer  {

    /**
     * Transform the given real data set.
     * @param f the real data array to be transformed (signal)
     * @return the real transformed array (spectrum)
     * @throws IllegalArgumentException if any parameters are invalid
     */
    double[] transform(double f[])
        throws IllegalArgumentException;

    /**
     * Transform the given real function, sampled on the given interval.
     * @param f the function to be sampled and transformed
     * @param min the lower bound for the interval
     * @param max the upper bound for the interval
     * @param n the number of sample points
     * @return the real transformed array
     * @throws IllegalArgumentException if any parameters are invalid
     */
    double[] transform(UnivariateFunction f, double min, double max, int n)
        throws IllegalArgumentException;

    /**
     * Inversely transform the given real data set.
     * @param f the real data array to be inversely transformed (spectrum)
     * @return the real inversely transformed array (signal)
     * @throws IllegalArgumentException if any parameters are invalid
     */
    double[] inverseTransform(double f[])
        throws IllegalArgumentException;

    /**
     * Inversely transform the given real function, sampled on the given interval.
     * @param f the function to be sampled and inversely transformed
     * @param min the lower bound for the interval
     * @param max the upper bound for the interval
     * @param n the number of sample points
     * @return the real inversely transformed array
     * @throws IllegalArgumentException if any parameters are invalid
     */
    double[] inverseTransform(UnivariateFunction f, double min, double max, int n)
        throws IllegalArgumentException;

}
