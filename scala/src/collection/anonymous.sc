package collection

object anonymous {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  def sum(f:Int=>Int, a:Int,b:Int): Int = {
		if (a>b) 0
		else f(a) + sum(f,a+1,b)
		}                                 //> sum: (f: Int => Int, a: Int, b: Int)Int
		
		sum((x:Int)=>x*x,1,4)             //> res0: Int = 30
		sum(x=>x*x,1,4)                   //> res1: Int = 30
		
}