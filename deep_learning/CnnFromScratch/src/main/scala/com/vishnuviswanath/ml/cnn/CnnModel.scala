package com.vishnuviswanath.ml.cnn

import java.io.File

import com.vishnuviswanath.ml.cnn.activations.{Relu, Sigmoid}
import com.vishnuviswanath.ml.cnn.layers._
import com.vishnuviswanath.ml.cnn.lossfunctions.{LossFunction, RMSE}
import com.vishnuviswanath.ml.cnn.optimizers.{GradientDescent, Optimizer}
import com.vishnuviswanath.ml.cnn.preprocessing.ImageReader.ImageWithClass
import com.vishnuviswanath.ml.cnn.preprocessing.{BatchReader, ImageReader}
import org.nd4j.linalg.api.ndarray.INDArray

/**
  * Created by vviswanath on 8/5/17.
  */
class CnnModel(layers: Array[Layer] = Array(),
               optimizer: Option[Optimizer] = None,
               lossFunction: Option[LossFunction] = None,
               debug: Boolean = false) {


  def withLayer(layer: Layer): CnnModel = {
    if (debug) layer.enableDebugging
    new CnnModel(layers :+ layer, optimizer, lossFunction, debug)
  }

  def withOptimizer(opt: Optimizer): CnnModel = {
    new CnnModel(layers, Some(opt), lossFunction, debug)
  }

  def withLossFunction(lossFunc: LossFunction): CnnModel = {
    new CnnModel(layers, optimizer, Some(lossFunc), debug)
  }

  def fit(imageReader: BatchReader, epochs: Int = 5): Unit = {
    if (layers.isEmpty || optimizer.isEmpty || lossFunction.isEmpty) {
      throw new RuntimeException("Incomplete model, either layers,optimizers or lossfunciton is not set")
    }

    def batchReaderStream(imageReader: BatchReader): Stream[Array[ImageWithClass]] = {
      if (imageReader.hasNext) {
        val nTup = imageReader.nextBatch
        nTup._1 #:: batchReaderStream(nTup._2)
      } else {
        Stream.empty
      }
    }

    val inputBatchStream = batchReaderStream(imageReader)
    for (i ← 0 until epochs) {
      println(s"epoch $i")
      inputBatchStream.foreach(batch ⇒{
        batch.foreach(imageWithClass ⇒  {
          val output = layers.foldLeft(imageWithClass._1)((image, layer) ⇒ layer.feedForward(image))
          println(output.getDouble(0), imageWithClass._2)
        })
      })
    }
  }

  override def toString: String = s"""${layers.mkString(" → \n")}"""
}

object TestCnnModel {
  import com.vishnuviswanath.ml.cnn.preprocessing.ImageReader.ImageBatchReader
  import org.nd4s.Implicits._

  def main(args: Array[String]): Unit = {

    /*val cnnModel = new CnnModel()
      .withLayer(Conv(2, inputShape = (28, 28, 3), Relu))
      .withLayer(MaxPooling(inputShape = (26, 26, 2)))  //26, 26, 2
      .withLayer(Flatten(inputShape= (13, 13, 2)))  //13 * 13 * 2 =
      .withLayer(Dense(5, inputSize = 338, Relu))
      .withLayer(Dense(1, inputSize = 5, Sigmoid))
      .withLossFunction(RMSE())
      .withOptimizer(GradientDescent())*/

    val cnnModel = new CnnModel()
      .withLayer(Conv(1, inputShape = (28, 28, 3), Relu))
      .withLayer(MaxPooling(inputShape = (26, 26, 1)))  //26, 26, 2
      .withLayer(Flatten(inputShape= (13, 13, 1)))  //13 * 13 * 2 =
      .withLayer(Dense(5, inputSize = 169, Relu))
      .withLayer(Dense(1, inputSize = 5, Sigmoid))
      .withLossFunction(RMSE())
      .withOptimizer(GradientDescent())

    val imageReader = ImageReader.getBatchReader("/Users/vviswanath/Downloads/mnist", 10)
    //val imageReader = ImageReader.getUnitBatchReader(ImageReader.read(new File("/Users/vviswanath/Downloads/mnist/0/img_111.jpg")), "0")


    cnnModel.fit(imageReader, 1)
    /*
    val imageReader = ImageReader.getUnitBatchReader((0 until 36).asNDArray(6, 6, 1))
    val simpleCnnModel = new CnnModel(debug = false)
      .withLayer(Conv(2, inputShape = (6, 6, 1), Relu)) //4,4,2
      .withLayer(MaxPooling(inputShape = (4, 4, 2)))  //
      .withLayer(Flatten(inputShape= (2, 2, 2)))  //2*2*2
      .withLayer(Dense(5, inputSize = 8, Relu)) //5
      .withLayer(Dense(1, inputSize = 5, Sigmoid))
      .withLossFunction(RMSE())
      .withOptimizer(GradientDescent())

    val result = simpleCnnModel.fit(imageReader, 1)
    */
  }
}
