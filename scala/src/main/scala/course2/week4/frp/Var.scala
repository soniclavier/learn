package course2.week4.frp

/**
  * Created by vishnu on 10/29/16.
  */
class Var[T](expr: => T) extends Signal[T](expr) {
  override def update(expr: => T) = super.update(expr)
}

object Var {
  def apply[T](expr: => T) = new Var(expr)
}
