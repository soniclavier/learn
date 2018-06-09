package com.vishnuviswanath.ml.cnn.layers

import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import org.nd4j.linalg.indexing.NDArrayIndex
import org.nd4s.Implicits._

/**
  * Created by vviswanath on 8/5/17.
  */
case class MaxPooling(inputShape: (Int, Int, Int), size: (Int, Int) = (2, 2)) extends Layer {
  override def feedForward(inputImage: INDArray): INDArray = {
    verifyInput(inputImage, inputShape)
    val height = inputShape._1
    val width = inputShape._2
    val depth = inputShape._3

    val output = Nd4j.createUninitialized(Array(height/size._1, width/size._2, depth))
    for (d ← 0 until depth) {
      for (i ← 0 until height - size._1 by size._1) {
        for (j ← 0 until width - size._2 by size._2) {
          val imageSubPlane = inputImage.get(NDArrayIndex.interval(i, i + size._1), NDArrayIndex.interval(j, j + size._2), NDArrayIndex.point(d))
          try {
            output.putScalar(i, j, d, imageSubPlane.maxNumber().doubleValue())
          } catch {
            case ex: Exception ⇒ //println(s"WARNING : MaxPooling padding not yet implemented. ${ex.getMessage}")
          }
        }
      }
    }
    if (debug) println(s"MaxPooling result: ${toString(output)}")
    output
  }

  override def toString: String = {
    s"MaxPooling[$inputShape → (${inputShape._1/size._1}, ${inputShape._2/size._2}, ${inputShape._3})]"
  }
}

object TestMaxPooling {
  def main(args: Array[String]): Unit = {
    val maxPoolingLayer = MaxPooling((5, 5, 3))
    val res = maxPoolingLayer.feedForward((0 until 75).asNDArray(5,5,3))
    res
  }
}
