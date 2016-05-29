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
  
  val m = Map("a" -> 97, "b" -> 98)               //> m  : scala.collection.immutable.Map[String,Int] = Map(a -> 97, b -> 98)
  m("a")                                          //> res1: Int = 97
  m get "c"                                       //> res2: Option[Int] = None
  m get "a"                                       //> res3: Option[Int] = Some(97)
  
  
  val l = List("c","b","aa","a","cccc")           //> l  : List[String] = List(c, b, aa, a, cccc)
  l.sorted                                        //> res4: List[String] = List(a, aa, b, c, cccc)
  l.sortWith(_.length < _.length)                 //> res5: List[String] = List(c, b, a, aa, cccc)
  l.sortWith((x,y) =>x.length < y.length)         //> res6: List[String] = List(c, b, a, aa, cccc)
  
  l groupBy (x => x.head)                         //> res7: scala.collection.immutable.Map[Char,List[String]] = Map(b -> List(b), 
                                                  //| a -> List(aa, a), c -> List(c, cccc))
  l groupBy (_.length)                            //> res8: scala.collection.immutable.Map[Int,List[String]] = Map(2 -> List(aa), 
                                                  //| 4 -> List(cccc), 1 -> List(c, b, a))
  val m1 = m withDefaultValue -1                  //> m1  : scala.collection.immutable.Map[String,Int] = Map(a -> 97, b -> 98)
  m1("c")                                         //> res9: Int = -1
  
	val seq = List((1,"a"),(2,"b"))           //> seq  : List[(Int, String)] = List((1,a), (2,b))
	seq.toMap                                 //> res10: scala.collection.immutable.Map[Int,String] = Map(1 -> a, 2 -> b)
}