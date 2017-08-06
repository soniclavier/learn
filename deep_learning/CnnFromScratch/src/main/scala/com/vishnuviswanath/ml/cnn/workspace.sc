import com.vishnuviswanath.ml.cnn.CnnModel
import com.vishnuviswanath.ml.cnn.activations._
import com.vishnuviswanath.ml.cnn.layers._
import com.vishnuviswanath.ml.cnn.lossfunctions.RMSE
import com.vishnuviswanath.ml.cnn.optimizers.GradientDescent
import com.vishnuviswanath.ml.cnn.preprocessing.ImageReader

val cnnModel = new CnnModel()
  .withLayer(Conv(2, inputShape = (28, 28, 3), Relu))
  .withLayer(MaxPooling(inputShape = (26, 26, 2)))  //26, 26, 2
  .withLayer(Flatten(inputShape= (14, 14, 2)))  //13 * 13 * 2 =
  .withLayer(Dense(5, inputSize = 392, Relu))
  .withLayer(Dense(1, inputSize = 5, Sigmoid))
  .withLossFunction(RMSE())
  .withOptimizer(GradientDescent())

val imageReader = ImageReader.getBatchReader("/Users/vviswanath/Downloads/mnist/0", 10)

cnnModel.fit(imageReader, 1)




