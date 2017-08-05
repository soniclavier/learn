import com.vishnuviswanath.ml.cnn.CnnModel
import com.vishnuviswanath.ml.cnn.layers._

val cnnModel = new CnnModel
cnnModel +
  Conv(2) +
  MaxPooling() +
  Flatten() +
  Dense(5) +
  Dense(1)

