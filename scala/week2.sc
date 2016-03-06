package week3

object rationals {
  val x = new Rational(1,3)                       //> x  : week3.Rational = 1/3
  x.numer                                         //> res0: Int = 1
  
  val y = new Rational(5,7)                       //> y  : week3.Rational = 5/7
  x.add(y)                                        //> res1: week3.Rational = 22/21
  
  val z = new Rational(3,2)                       //> z  : week3.Rational = 3/2
  
  x.sub(y).sub(z)                                 //> res2: week3.Rational = -79/42
  
  x.neg                                           //> res3: week3.Rational = -1/3
}

class Rational(x: Int,y:Int) {
	def numer = x
	def denom = y
	
	def add(that: Rational) =
		new Rational(numer*that.denom + that.numer*denom,
		denom*that.denom)
		
	def neg = new Rational(-1*numer,denom)
	
	def sub(that: Rational) =
		new Rational(numer*that.denom-that.numer*denom,
		denom*that.denom)
	
	override def toString = numer+"/"+denom
}