package week6

object polynomials {
  class Poly(val terms: Map[Int,Double]) {
  	def + (other: Poly) = {
  		new Poly(terms ++ (other.terms map adjust))
  		}
  		def adjust(term: (Int,Double)): (Int,Double) = {
  		val (exp,coeff) = term
  		terms get exp match {
  		 case Some(coeff1) => exp -> (coeff + coeff1)
  		 case None => exp -> coeff
  		}
  	
  	}
  	
  	override def toString = terms map ( kv => kv._2+"x^"+kv._1) mkString " + "
  }
  
  val p1 = new Poly(Map(1->2.0,3->4.0,5->6.2))    //> p1  : week6.polynomials.Poly = 2.0x^1 + 4.0x^3 + 6.2x^5
  val p2 = new Poly(Map(0->3.0,3->7.0))           //> p2  : week6.polynomials.Poly = 3.0x^0 + 7.0x^3
  
  
  p1 + p2                                         //> res0: week6.polynomials.Poly = 2.0x^1 + 11.0x^3 + 6.2x^5 + 3.0x^0
}