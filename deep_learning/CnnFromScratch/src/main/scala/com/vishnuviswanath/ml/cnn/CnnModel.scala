package com.vishnuviswanath.ml.cnn

import com.vishnuviswanath.ml.cnn.layers.Layer

/**
  * Created by vviswanath on 8/5/17.
  */
class CnnModel(layers: Array[Layer] = Array()) {

  def +(layer: Layer): CnnModel = {
    new CnnModel(layers :+ layer)
  }

  def setOptimizer(opt: Optimizer)

  override def toString: String = layers.mkString(" → ")
}
