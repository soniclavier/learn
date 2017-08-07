package com.vishnuviswanath.ml.cnn.preprocessing

import com.vishnuviswanath.ml.cnn.preprocessing.ImageReader.ImageWithClass
import org.nd4j.linalg.api.ndarray.INDArray

/**
  * Created by vviswanath on 8/5/17.
  */
trait BatchReader {

  def hasNext: Boolean
  def nextBatch: (Array[ImageWithClass],BatchReader)

}
