package week4

object exprs {
  def show(e: Expr): String  = e match {
		case Number(x) => x.toString
		case Sum(l,r) => show(l) + " + "+show(r)
		case Var(x) => x.toString
		case Prod(l,r) => show(l) +" * "+show(r)
	}
  
  def eval(e: Expr): Int = {
    if (e.isNumber) e.numValue
    else if (e.isSum) eval(e.leftOp)+eval(e.rightOp)
    else if (e.isProd) eval(e.leftOp)*eval(e.rightOp)
    else throw new Error("unknown excpression "+e)
  }

}


trait Expr {
  def isNumber: Boolean
  def isSum: Boolean
  def isVar: Boolean
  def isProd: Boolean
  def varName: String
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr
}
case class Number(n:Int) extends Expr {
  def isNumber = true
  def isSum = false
  def isVar = false
  def isProd = false
  def varName = throw new Exception("not a variable")
  def numValue = n
  def leftOp = throw new Exception("leftop")
  def rightOp = throw new Exception("rightop")
}
case class Sum(e1:Expr,e2:Expr) extends Expr {
  def isNumber = false
  def isSum = true
  def isVar = false
  def isProd = false
  def varName = throw new Exception("not a variable")
  def numValue = throw new Exception("not a num")
  def leftOp = e1
  def rightOp = e2
}
case class Var(x:String) extends Expr {
  def isNumber = false
  def isSum = false
  def isVar = true
  def isProd = false
  def varName = x
  def numValue = throw new Exception("not a num")
  def leftOp = throw new Exception("leftop")
  def rightOp = throw new Exception("rightop")
}
case class Prod(e1:Expr,e2:Expr) extends Expr {
  def isNumber = false
  def isSum = false
  def isVar = false
  def isProd = true
  def varName = throw new Exception("not a variable")
  def numValue = throw new Exception("not a num")
  def leftOp = e1
  def rightOp = e2
  
}