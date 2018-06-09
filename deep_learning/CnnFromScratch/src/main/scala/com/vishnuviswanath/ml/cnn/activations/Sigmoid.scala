package com.vishnuviswanath.ml.cnn.activations

/**
  * Created by vviswanath on 8/6/17.
  */
object Sigmoid extends ActivationFunction {
  override def apply(x: Double): Double = {
    1 / (1 + Math.exp(-x))
  }

  /**
    * sigmoid(x) = 1 / (1 + e.pow(-x))
    * (d/dx)(sigmoid(x)) = e.pow(-x)/ (1 + e.pow(-x)).pow(2)
    * @param x
    * @return
    */
  def derivative(x: Double): Double = {
    Math.exp(-x) / Math.pow(1 + Math.exp(-x), 2)
  }
}
