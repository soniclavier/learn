package com.vishnuviswanath.ml.cnn.activations

/**
  * Created by vviswanath on 8/6/17.
  */
object Sigmoid extends ActivationFunction {
  override def apply(x: Double): Double = {
    1 / (1 + Math.exp(-x))
  }
}
