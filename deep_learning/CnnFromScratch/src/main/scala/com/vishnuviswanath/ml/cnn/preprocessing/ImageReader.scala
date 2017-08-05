package com.vishnuviswanath.ml.cnn.preprocessing

import java.io.File
import javax.imageio.ImageIO

/**
  * Created by vviswanath on 8/5/17.
  */
object ImageReader {

  private def read(file: File): Array[Array[Array[Int]]] = {
    val image = ImageIO.read(file)
    val height = image.getHeight
    val width = image.getWidth

    val reds = Array.ofDim[Int](width, height)
    val greens = Array.ofDim[Int](width, height)
    val blues = Array.ofDim[Int](width, height)

    val rgb3d = Array.ofDim[Int](width, height, 3)

    val colors = for {
      c ← Range(0, 3)
      h ← Range(0, height)
      w ← Range(0, width)
    } {
      val color = Color(image.getRGB(w, h))
      c match {
        case 0 ⇒ rgb3d(w)(h)(0) = color.red
        case 1 ⇒ rgb3d(w)(h)(1) = color.green
        case 2 ⇒ rgb3d(w)(h)(2) = color.blue
      }
    }
    rgb3d
  }


  case class Color(rgb: Int) {
    val value: Int = 0xff000000 | rgb

    def red: Int = (value >> 16) & 0xFF
    def green: Int = (value >> 8) & 0xFF
    def blue: Int = (value >> 0) & 0xFF
  }


  def getBatchReader(dir: String, batchSize: Int): BatchReader = {
    val files = new File(dir).listFiles.filter(f ⇒ f.getName.endsWith("png") || f.getName.endsWith("jpg"))

    def batchedFileStream(start: Int): Stream[Array[File]] = {
      val filesInBatch = (start until Math.min(files.length, start + batchSize))
        .map(i ⇒ files(i)).toArray
      filesInBatch #:: batchedFileStream(start + batchSize)
    }

    new BatchReader(batchedFileStream(0), files, files.length, batchSize)
  }

  class BatchReader(stream: Stream[Array[File]],
                            files: Array[File],
                            remaining: Int,
                            batchSize: Int)  {

    def hasNext: Boolean = remaining > 0
    def nextBatch: (Array[Array[Array[Array[Int]]]], BatchReader) = {
      println(s"Reading next batch")
      val nBatch = stream.head.map(f ⇒ {
        println(s"Reading file : ${f.getName}")
        read(f)
      })
      (nBatch, new BatchReader(stream.tail, files, remaining - batchSize, batchSize))
    }
  }


  def main(args: Array[String]): Unit = {
    var batchReader = ImageReader.getBatchReader("/Users/vviswanath/Downloads/mnist/0", 10)

    while(batchReader.hasNext) {
      batchReader = batchReader.nextBatch._2
    }

  }
}
