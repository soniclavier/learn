package com.vishnuviswanath.ml.cnn

import com.vishnuviswanath.ml.cnn.layers.Layer
import com.vishnuviswanath.ml.cnn.lossfunctions.LossFunction
import com.vishnuviswanath.ml.cnn.optimizers.Optimizer
import com.vishnuviswanath.ml.cnn.preprocessing.ImageReader.ImageBatchReader

/**
  * Created by vviswanath on 8/5/17.
  */
class CnnModel(layers: Array[Layer] = Array(),
               optimizer: Option[Optimizer] = None,
               lossFunction: Option[LossFunction] = None) {


  def withLayer(layer: Layer): CnnModel = {
    new CnnModel(layers :+ layer, optimizer, lossFunction)
  }

  def withOptimizer(opt: Optimizer): CnnModel = {
    new CnnModel(layers, Some(opt), lossFunction)
  }

  def withLossFunction(lossFunc: LossFunction): CnnModel = {
    new CnnModel(layers, optimizer, lossFunction)
  }

  def fit(imageReader: ImageBatchReader, epochs: Int = 5): Unit = {

  }


  override def toString: String = s"""${layers.mkString(" → \n")}"""
}
