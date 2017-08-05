name := "CnnFromScratch"

version := "1.0"

scalaVersion := "2.11.7"

val nd4jVersion = "0.8.0"

resolvers += Resolver.mavenLocal
libraryDependencies += "org.nd4j" % "nd4j-native-platform" % nd4jVersion
libraryDependencies += "org.nd4j" %% "nd4s" % nd4jVersion
