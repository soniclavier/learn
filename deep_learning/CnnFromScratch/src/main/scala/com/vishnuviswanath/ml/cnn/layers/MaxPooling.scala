package com.vishnuviswanath.ml.cnn.layers

import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import org.nd4j.linalg.indexing.NDArrayIndex
import org.nd4s.Implicits._

/**
  * Created by vviswanath on 8/5/17.
  */
case class MaxPooling(size: (Int, Int) = (2, 2)) extends Layer {
  override def applyLayer(input: INDArray): INDArray = {
    val height = input.shape()(0)
    val width = input.shape()(1)
    val depth = input.shape()(2)

    val output = Nd4j.createUninitialized(Array((height/size._1) + 1, (width/size._2) + 1, depth))
    for (d ← 0 until depth) {
      for (i ← 0 to height - size._1) {
        for (j ← 0 to width - size._2) {
          val imageSubPlane = input.get(NDArrayIndex.interval(i, i + size._1), NDArrayIndex.interval(j, j + size._2), NDArrayIndex.point(d))
          output.putScalar(i, j, d, imageSubPlane.maxNumber().doubleValue())
        }
      }
    }
    output
  }
}

object TestMaxPooling {
  def main(args: Array[String]): Unit = {
    val maxPoolingLayer = MaxPooling()
    val res = maxPoolingLayer.applyLayer((0 until 27).asNDArray(3,3,3))
    res
  }
}
