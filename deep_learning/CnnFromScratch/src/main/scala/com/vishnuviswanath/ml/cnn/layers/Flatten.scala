package com.vishnuviswanath.ml.cnn.layers

import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4s.Implicits._

/**
  * Created by vviswanath on 8/5/17.
  */
case class Flatten(inputShape: (Int, Int, Int)) extends Layer {
  val size: Int = inputShape._1 * inputShape._2 * inputShape._3

  def applyLayer(inputImage: INDArray): INDArray = {
    verifyInput(inputImage, inputShape)
    val output = inputImage.reshape(1, size)
    if (debug) println(s"Flatten result: ${toString(output)}")
    output
  }

  override def toString: String = {
    s"Flatten[$inputShape → (1, ${inputShape._1 * inputShape._2 * inputShape._3})]"
  }
}

object TestFlatten {
  def main(args: Array[String]): Unit = {
    val flatten = Flatten((3, 3, 1))
    val res = flatten.applyLayer((0 to 9).asNDArray(3, 3 ,1))
    res
  }
}
