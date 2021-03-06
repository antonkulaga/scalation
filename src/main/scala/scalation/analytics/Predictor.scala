
//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/** @author  John Miller
 *  @version 1.2
 *  @date    Sun Sep 23 21:14:14 EDT 2012
 *  @see     LICENSE (MIT style license file).
 */

package scalation.analytics

import scalation.linalgebra.{MatriD, VectorD, VectorI}

//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/** The `Predictor` trait provides a common framework for several predictors.
 */
trait Predictor
{
    /** Coefficient/parameter vector [b_0, b_1, ... b_k]
     */
    protected var b: VectorD = null

    /** Residual/error vector [e_0, e_1, ... e_m-1]
     */
    protected var e: VectorD = null

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Given a set of data vectors 'x's and their corresponding responses 'y's,
     *  train the prediction function 'y = f(x)' by fitting its parameters.
     */
    def train ()

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Return the vector of coefficient/parameter values.
     */
    def coefficient: VectorD = b

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Return the vector of residuals/errors.
     */
    def residual: VectorD = e

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Return the quality of fit including rSquared.
     */
    def fit: VectorD

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Return the labels for the fit.  Override when necessary.
     */
    def fitLabels: Array [String] = Array ("rSquared", "rBarSq", "fStat")

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Given a new continuous data vector z, predict the y-value of f(z).
     *  @param z  the vector to use for prediction
     */
    def predict (z: VectorD): Double

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Given a new discrete data vector z, predict the y-value of f(z).
     *  @param z  the vector to use for prediction
     */
    def predict (z: VectorI): Double = predict (z.toDouble)

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Given several new continuous data vectors stored as rows in a matrix,
     *  predict all the 'y'-values of 'predict (z_i)'.  May override for efficiency.
     *  @param z  the matrix containing row vectors to use for prediction
     */
    def predict (z: MatriD): VectorD =
    {
        VectorD (for (i <- 0 until z.dim1) yield predict (z(i)))
    } // predict

} // Predictor trait

