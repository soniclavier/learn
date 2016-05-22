package week4.idealized

abstract class Boolean {
  
  def ifThenElse[T](t: => T,e: => T): T
  
  def &&(x: => Boolean): Boolean = ifThenElse(x,False)
  def ||(x: => Boolean): Boolean = ifThenElse(True,x)
  def unary_! : Boolean = ifThenElse(False,True)
  def ==(x: Boolean): Boolean = ifThenElse(x,x.unary_!)
  def !=(x: Boolean): Boolean = ifThenElse(x.unary_!,x)
  
  def <(x: Boolean): Boolean = ifThenElse(False,x)
  
}

object True extends Boolean {
  def ifThenElse[T](t: => T,e: => T) = t
  override def toString = "True Object"
}

object False extends Boolean {
  def ifThenElse[T](t: => T,e: => T) = e
  override def toString = "False Object"
}

