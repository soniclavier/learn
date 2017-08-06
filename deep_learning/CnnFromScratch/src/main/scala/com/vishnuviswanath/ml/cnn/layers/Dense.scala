package com.vishnuviswanath.ml.cnn.layers

import com.vishnuviswanath.ml.cnn.activations.{ActivationFunction, Relu}
import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import org.nd4s.Implicits._

/**
  * Created by vviswanath on 8/5/17.
  */
case class Dense(size: Int, inputSize: Int, func: ActivationFunction) extends Layer {
  val weights: INDArray = Nd4j.rand(1, inputSize)

  override def applyLayer(input: INDArray): INDArray = {
    val output = Nd4j.createUninitialized(size)
    for(i ← 0 until size)  {
      val value = func((0 until inputSize).map(j ⇒ {
        input.getDouble(j) * weights.getDouble(i)
      }).sum)
      output.putScalar(i, value)
    }
    if (debug) println(s"Dense result: ${toString(output)}")
    output
  }

  override def toString: String = {
    s"Dense[$inputSize → $size]"
  }
}


object TestDense {
  def main(args: Array[String]): Unit ={
    val dl = new Dense(5, 10, Relu)
    val res = dl.applyLayer((-1 to 9).toNDArray)
    res
  }
}