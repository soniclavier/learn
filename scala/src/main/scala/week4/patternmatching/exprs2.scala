package week4.patternmatching

class exprs2 {
  def eval(e: Expr): Int = e match {
    case Number(n) => n
    case Sum(e1,e2) => eval(e1)+eval(e2)
  }
  
  def show(e: Expr): String = e match {
    case Number(n) => n.toString
    case Prod(e1,e2) =>  
      "%s * %s".format(
      e1 match {
        case Sum(x,y) => "( "+show(e1)+" )"
        case _ => show(e1)
      },
      e2 match {
        case Sum(x,y) => "( "+show(e2)+" )"
        case _ => show(e2)
      })
    
    case Sum(e1,e2) => show(e1) +" + "+show(e2)
    case Var(x) => x.toString
  }
}

trait Expr 

//scala will implicity add companion Objects for case classes
//e.g.,
//object Number {
// def apply(n: Int) = new Number(n)
//}

//this is the reason why we are able to create an new object by just calling.  Number(2), the same is applicable if you define an object.
//because scala will create an apply method. but for classes we need to call it using new ClassName()
case class Number(n:Int) extends Expr
case class Sum(e1: Expr,e2: Expr) extends Expr
case class Var(n: String) extends Expr
case class Prod(e1: Expr,e2: Expr) extends Expr