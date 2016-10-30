package course2.week2

object worksheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val xs = Stream.cons(1, Stream.cons(2, Stream.cons(3,Stream.empty)))
                                                  //> xs  : Stream.Cons[Int] = Stream(1, ?)
  val ys = List(1,2,3)                            //> ys  : List[Int] = List(1, 2, 3)
  
  
  0 #:: xs                                        //> res0: scala.collection.immutable.Stream[Int] = Stream(0, ?)
  
  def streamRange(lo: Int, hi: Int): Stream[Int] = {
  	if (lo < hi) Stream.empty
  	else Stream.cons(lo, streamRange(lo+1, hi))
	}                                         //> streamRange: (lo: Int, hi: Int)Stream[Int]
}