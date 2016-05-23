package week5

object worksheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def removeAt[T](n: Int, xs: List[T]): List[T] = {

    //(xs take n) ::: (xs drop n+1)
    if (n > xs.size) throw new Exception("out of bound")
    def iter(k: Int, xxs: List[T]): List[T] = {

      if (k == n) xxs.tail
      else xxs.head :: iter(k + 1, xxs.tail)
    }
    iter(1, xs)
  }                                               //> removeAt: [T](n: Int, xs: List[T])List[T]

  def flatten(xs: List[Any]): List[Any] = xs match {
    case List() => xs
    case (y:List[Any]) :: ys => flatten(y) ::: flatten(ys)
    case y :: ys => y :: flatten(ys)
  }                                               //> flatten: (xs: List[Any])List[Any]

  removeAt[Int](1, List(1, 2, 3))                 //> res0: List[Int] = List(2, 3)
  flatten(List(1, 2, 3, List(4, 5)))              //> res1: List[Any] = List(1, 2, 3, 4, 5)

}