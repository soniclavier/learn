package com.vishnuviswanath.ml.cnn.layers

import org.nd4j.linalg.api.ndarray.INDArray

/**
  * Created by vviswanath on 8/5/17.
  */
trait Layer {

  var debug: Boolean = false
  def enableDebugging: Unit = debug = true

  def feedForward(input: INDArray): INDArray

  def verifyInput(input: INDArray, shape: (Int, Int, Int)): Unit = {
    val inputShape = input.shape
    if (!(inputShape(0) == shape._1 && inputShape(1) == shape._2 && inputShape(2) == shape._3)) {
      throw new RuntimeException(s"$this input shape[(${inputShape.mkString(",")})] does not match expected shape [$shape]")
    }
  }

  def toString(input: INDArray): String = {
    s"Shape : ${input.shape().mkString(",")}\n Content : ${input.toString}"
  }

}
