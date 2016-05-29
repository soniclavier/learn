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
	
	
	def mapFun[T,U](xs: List[T],f: T => U): List[U] = {
		(xs foldRight List[U]())((x,y) => f(x) :: mapFun(xs.tail,f))
	}                                         //> mapFun: [T, U](xs: List[T], f: T => U)List[U]
	
	mapFun(nums,(x:Int) => (x+1))             //> res8: List[Int] = List(3, -3, 6, 8, 2)
	
	def lengthFun[T](xs: List[T]): Int = {
		(xs foldRight 0)((x,y) => y+1)
	}                                         //> lengthFun: [T](xs: List[T])Int
	
	lengthFun(nums)                           //> res9: Int = 5
	
	List(1,2,3,4) reduceLeft (_+_)            //> res10: Int = 10
	(List(1,2,3,4) foldLeft -1)(_+_)          //> res11: Int = 9
	(List() foldLeft -1)((x:Int,y:Int) => x+y)//> res12: Int = -1
	
	val (sum,count) = (List(1,2,3,4) foldLeft (0.0,0))((x,y) => (x._1+y,x._2+1))
                                                  //> sum  : Double = 10.0
                                                  //| count  : Int = 4
	sum/count                                 //> res13: Double = 2.5
	
	List(1,2,3,4) reduceRight (_+_)           //> res14: Int = 10
	(List(1,2,3,4) foldRight 0)(_+_)          //> res15: Int = 10
  
}