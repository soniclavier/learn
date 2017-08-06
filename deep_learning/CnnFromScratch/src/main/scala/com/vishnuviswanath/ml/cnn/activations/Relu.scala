package com.vishnuviswanath.ml.cnn.activations

/**
  * Created by vviswanath on 8/6/17.
  */
object Relu extends ActivationFunction {
  override def apply(x: Double): Double = {
    Math.max(0, x)
  }
}
