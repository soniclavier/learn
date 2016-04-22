package week4

object exprs {
  def show(e: Expr): String  = e match {
		case Number(x) => x.toString
		case Sum(l,r) => show(l) + " + "+show(r)
		case Var(x) => x.toString
		case Prod(l,r) => show(l) +" * "+show(r)
	}

}


trait Expr
case class Number(n:Int) extends Expr
case class Sum(e1:Expr,e2:Expr) extends Expr
case class Var(x:String) extends Expr
case class Prod(e1:Expr,e2:Expr) extends Expr