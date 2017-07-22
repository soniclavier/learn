#import the modules
from keras.models import Sequential
from keras.layers.convolutional import Conv2D
from keras.layers.pooling import MaxPooling2D
from keras.layers.core import Flatten
from keras.layers.core import Dense
from keras.preprocessing.image import ImageDataGenerator

#initialize the classifier
classifier = Sequential()

#add layers
classifier.add(Conv2D(32, (3, 3), input_shape=(64, 64, 3), activation = 'relu')) 
classifier.add(MaxPooling2D()) #default pool size is (2, 2)
classifier.add(Flatten()) #flatten all layers into a single layer
classifier.add(Dense(128, activation = 'relu'))
classifier.add(Dense(1, activation = 'sigmoid')) #2 = number of outputs

classifier.compile(optimizer = 'adam', 
	loss = 'binary_crossentropy', 
	metrics = ['accuracy'])

#image pre-processing
train_datagen = ImageDataGenerator(
	rescale=1./255,
	shear_range=0.2,
	zoom_range=0.2,
	horizontal_flip=True)

test_datagen = ImageDataGenerator(rescale=1./255)

train_generator = train_datagen.flow_from_directory(
	'dataset/training_set',
	target_size=(64, 64),
	batch_size=32,
	class_mode='binary')

test_generator = test_datagen.flow_from_directory(
	'dataset/test_set',
	target_size=(64, 64),
	batch_size=32,
	class_mode='binary')

classifier.fit_generator(
	train_generator,
	steps_per_epoch=8000,
	epochs=25,
	validation_data=test_generator,
	validation_steps=2000)