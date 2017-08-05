package com.vishnuviswanath.ml.cnn.layers

/**
  * Created by vviswanath on 8/5/17.
  */
case class Conv(filters: Int,
                filterShape: (Int, Int) = (3, 3),
                stride: Int = 1,
                inputShape: Option[(Int, Int, Int)] = None) extends Layer {

}
