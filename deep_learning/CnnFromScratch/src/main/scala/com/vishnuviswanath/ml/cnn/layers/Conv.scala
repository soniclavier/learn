package com.vishnuviswanath.ml.cnn.layers

import com.vishnuviswanath.ml.cnn.activations.{ActivationFunction, Relu}
import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import org.nd4j.linalg.indexing.NDArrayIndex
import org.nd4s.Implicits._

import scala.collection.immutable.IndexedSeq

/**
  * Created by vviswanath on 8/5/17.
  */
case class Conv(numFilters: Int,
                inputShape: (Int, Int, Int),
                actFunction: ActivationFunction,
                filterShape: (Int, Int) = (3, 3),
                stride: Int = 1) extends Layer {

  /**
    * for an input image with 3 channels, and filter shape of (3,3)
    * then #numFilters (3 x 3 x 3) filters are created
    *
    */

  //Will filters evolve differently if initialized with same weights
  val filters: IndexedSeq[INDArray] = for {i ← 0 until numFilters} yield Nd4j.rand(Array(filterShape._1, filterShape._2, inputShape._3))

  def applyLayer(inputImage: INDArray): INDArray = {
    verifyInput(inputImage, inputShape)
    val imgHeight = inputShape._1
    val imgWidth = inputShape._2
    val filterHeight = filterShape._1
    val filterWidth = filterShape._2
    val convResult: INDArray = Nd4j.createUninitialized(Array(imgHeight - filterHeight + 1, imgWidth - filterWidth + 1, numFilters))
    for(i ← Range(0, imgHeight - filterHeight, step = stride)) {
      for (j ← Range(0, imgWidth - filterWidth, step = stride)) {
        val subImageSpace = inputImage.get(NDArrayIndex.interval(i, i + filterHeight), NDArrayIndex.interval(j, j + filterWidth), NDArrayIndex.all())
        for (k ← Range(0, numFilters)) {
          val filter3d = filters(k)
          val convValue = filter3d.dup()
          convValue.muli(subImageSpace)
          convResult.putScalar(i, j, k, actFunction(convValue.sumNumber().doubleValue()))
        }
      }
    }
    if (debug) println(s"ConvLayer result: ${toString(convResult)}")
    convResult
  }

  override def toString: String = {
    s"Conv Layer[$inputShape → (${inputShape._1 - filterShape._1 +1}, ${inputShape._2 - filterShape._2 + 1}, $numFilters)]"
  }
}

object TestConv {
  def main(args: Array[String]): Unit = {
    val conv = Conv(1, inputShape = (5, 5, 1), actFunction= Relu)
    val img = (1 to 25).asNDArray(5, 5, 1)
    val res = conv.applyLayer(img)
    res
    print(res)
  }
}
