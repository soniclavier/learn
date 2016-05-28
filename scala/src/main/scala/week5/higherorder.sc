package week5

object higherorder {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val nums = List(2, -4, 5, 7, 1)                 //> nums  : List[Int] = List(2, -4, 5, 7, 1)
  
  val letters = List("a", "a","a","b", "c","c","a")
                                                  //> letters  : List[String] = List(a, a, a, b, c, c, a)
  val fruits = List("apple", "pineapple", "orange", "banana")
                                                  //> fruits  : List[String] = List(apple, pineapple, orange, banana)
  nums filter (x => x > 0)                        //> res0: List[Int] = List(2, 5, 7, 1)
  nums filterNot (x => x > 0)                     //> res1: List[Int] = List(-4)
  nums partition (x => x > 0)                     //> res2: (List[Int], List[Int]) = (List(2, 5, 7, 1),List(-4))

  //stops when a wrong condition in encountered
  nums takeWhile (x => x > 0)                     //> res3: List[Int] = List(2)
  nums dropWhile (x => x > 0)                     //> res4: List[Int] = List(-4, 5, 7, 1)
  nums span (x => x > 0)                          //> res5: (List[Int], List[Int]) = (List(2),List(-4, 5, 7, 1))

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil      => Nil
    case x :: xs1 =>
    	val (first,last) = xs span (y => y == x)
    	first :: pack(last)
  }                                               //> pack: [T](xs: List[T])List[List[T]]
  
  pack(letters)                                   //> res6: List[List[String]] = List(List(a, a, a), List(b), List(c, c), List(a))
                                                  //| 
  
  def encode[T](xs: List[T]) : List[(T,Int)] = {
  	pack(xs) map (xs1 => (xs1.head,xs1.length))
  	 
  }                                               //> encode: [T](xs: List[T])List[(T, Int)]

	encode(letters)                           //> res7: List[(String, Int)] = List((a,3), (b,1), (c,2), (a,1))
}