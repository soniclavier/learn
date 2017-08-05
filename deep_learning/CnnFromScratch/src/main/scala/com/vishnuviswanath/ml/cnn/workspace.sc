import com.vishnuviswanath.ml.cnn.CnnModel
import com.vishnuviswanath.ml.cnn.layers._
import com.vishnuviswanath.ml.cnn.lossfunctions.RMSE
import com.vishnuviswanath.ml.cnn.optimizers.GradientDescent

val cnnModel = new CnnModel()
  .withLayer(Conv(2))
  .withLayer(MaxPooling())
  .withLayer(Flatten())
  .withLayer(Dense(5))
  .withLayer(Dense(1))
  .withLossFunction(RMSE())
  .withOptimizer(GradientDescent())





