package com.vishnuviswanath.ml.cnn.preprocessing

import java.io.File
import javax.imageio.ImageIO

import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
/**
  * Created by vviswanath on 8/5/17.
  */
object ImageReader {

  def read(file: File): INDArray = {
    val image = ImageIO.read(file)
    val height = image.getHeight
    val width = image.getWidth

    val reds = Array.ofDim[Int](width, height)
    val greens = Array.ofDim[Int](width, height)
    val blues = Array.ofDim[Int](width, height)

    val ndArr = Nd4j.createUninitialized(Array(width, height, 3))
    val pixels = for {
      c ← Range(0, 3)
      h ← Range(0, height)
      w ← Range(0, width)
    } yield(w, h, c)

    pixels.foldLeft(ndArr)((nd, p) ⇒ {
      val color = Color(image.getRGB(p._1, p._2))
      val value = p._3 match {
        case 0 ⇒ color.red
        case 1 ⇒ color.green
        case 2 ⇒ color.blue
      }
      ndArr.putScalar(p._1, p._2, p._3, value)
    })
    ndArr
  }


  case class Color(rgb: Int) {
    val value: Int = 0xff000000 | rgb

    def red: Int = (value >> 16) & 0xFF
    def green: Int = (value >> 8) & 0xFF
    def blue: Int = (value >> 0) & 0xFF
  }

  def getUnitBatchReader(image: INDArray): BatchReader = {
    new UnitBatchReader(image, 1)
  }

  class UnitBatchReader(image: INDArray, remaining: Int) extends BatchReader {
    override def hasNext: Boolean = remaining > 0
    override def nextBatch: (Array[INDArray], BatchReader) = {
      (Array(image), new UnitBatchReader(null, 0))
    }
  }

  def getBatchReader(dir: String, batchSize: Int): BatchReader = {
    val files = new File(dir).listFiles.filter(f ⇒ f.getName.endsWith("png") || f.getName.endsWith("jpg"))

    def batchedFileStream(start: Int): Stream[Array[File]] = {
      val filesInBatch = (start until Math.min(files.length, start + batchSize))
        .map(i ⇒ files(i)).toArray
      filesInBatch #:: batchedFileStream(start + batchSize)
    }
    new ImageBatchReader(batchedFileStream(0), files, files.length, batchSize)
  }

  class ImageBatchReader(stream: Stream[Array[File]],
                         files: Array[File],
                         remaining: Int,
                         batchSize: Int)  extends BatchReader {

    def hasNext: Boolean = remaining > 0
    def nextBatch: (Array[INDArray], BatchReader) = {
      println(s"Reading next batch")
      val nBatch = stream.head.map(f ⇒ {
        println(s"Reading file : ${f.getName}")
        read(f)
      })
      (nBatch, new ImageBatchReader(stream.tail, files, remaining - batchSize, batchSize))
    }

  }


  def main(args: Array[String]): Unit = {
    var batchReader = ImageReader.getBatchReader("/Users/vviswanath/Downloads/mnist/0", 10)

    while(batchReader.hasNext) {
      batchReader = batchReader.nextBatch._2
    }

  }
}
