package com.vishnuviswanath.ml.cnn.activations

/**
  * Created by vviswanath on 8/6/17.
  */
trait ActivationFunction {
  def apply(x: Double): Double
}
