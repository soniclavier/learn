package com.vishnuviswanath.ml.cnn.layers

import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4s.Implicits._

/**
  * Created by vviswanath on 8/5/17.
  */
case class Flatten() extends Layer {
  def applyLayer(input: INDArray) = {
    val size = input.shape().product
    input.reshape(1, size)
  }
}

object TestFlatten {
  def main(args: Array[String]): Unit = {
    val flatten = Flatten()
    val res = flatten.applyLayer((0 to 9).asNDArray(3, 3))
    res
  }
}
