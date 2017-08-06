package com.vishnuviswanath.ml.cnn.layers

import org.nd4j.linalg.api.ndarray.INDArray

/**
  * Created by vviswanath on 8/5/17.
  */
trait Layer {
  def applyLayer(input: INDArray): INDArray
}
