package week4
import week4._
object week4worksheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
	def nth[T](n:Int,l: List[T]) : T =
		if (l.isEmpty) throw new IndexOutOfBoundsException
		else if (n == 0) l.head
		else nth(n-1,l.tail)              //> nth: [T](n: Int, l: week4.List[T])T
		
	//val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
 //	nth(2,list)
//	nth(-1,list)
	
	def testprint() : String = "test"         //> testprint: ()String
 testprint                                        //> res0: String = test
 exprs.show(Sum(Number(1),Number(44)))            //> res1: String = 1 + 44
}