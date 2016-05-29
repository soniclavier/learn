package week6

object worksheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  def scalarProduct(xs: List[Double], ys: List[Double]): Double =  {
  	(for {
  		(x,y) <- xs zip ys
  	} yield x*y).sum
  }                                               //> scalarProduct: (xs: List[Double], ys: List[Double])Double
  
  val x = List(1.0,2.0,3.0)                       //> x  : List[Double] = List(1.0, 2.0, 3.0)
  val y = List(4.0,5.0,6.0)                       //> y  : List[Double] = List(4.0, 5.0, 6.0)
  scalarProduct(x,y)                              //> res0: Double = 32.0
  
  val s = Set("a","b","c","a")                    //> s  : scala.collection.immutable.Set[String] = Set(a, b, c)
}