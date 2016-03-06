package week1

object session {
  1 + 3                                           //> res0: Int(4) = 4
  def abs(x: Double) = if (x < 0) -x else x       //> abs: (x: Double)Double

  def sqrt(x: Double) = {
    def sqrtIter(guess: Double): Double =
      if (isGoodEnough(guess)) guess
      else sqrtIter(improve(guess))

    def isGoodEnough(guess: Double) =
      abs(guess * guess - x) < 0.001

    def improve(guess: Double) =
      (guess + x / guess) / 2

    sqrtIter(1.0)
  }                                               //> sqrt: (x: Double)Double

  sqrt(2)                                         //> res1: Double = 1.4142156862745097
  
  def sum(f:Int=>Int,a:Int,b:Int):Int = {
  	def loop(a:Int,acc:Int):Int = {
  		if(a>b) acc
  		else loop(a+1,f(a)+acc)
  	}
  	loop(a,0)
  }                                               //> sum: (f: Int => Int, a: Int, b: Int)Int
  sum(x=>x*x,3, 5)                                //> res2: Int = 50
  
 
		
  def product(f:Int => Int)(a:Int,b:Int):Int = mapReduce(f,(x,y)=>x*y,1)(a,b)
                                                  //> product: (f: Int => Int)(a: Int, b: Int)Int
  def mapReduce(f:Int => Int, combine: (Int,Int)=>Int,zero:Int)(a:Int,b:Int): Int =
		if (a>b) zero
		else combine(f(a),mapReduce(f,combine,zero)(a+1,b))
                                                  //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b:
                                                  //|  Int)Int
	
	product(x=>x*x)(3,4)                      //> res3: Int = 144
	
	def factorial(n:Int)  = product(x=>x)(1,n)//> factorial: (n: Int)Int
	factorial(5)                              //> res4: Int = 120
	
	
		
		
}