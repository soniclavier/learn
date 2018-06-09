/*
import cats._ , cats.instances._ , cats.implicits._


//Semigroup - An algebraic structure (a binary operation, a set)  which follows closure property & is associative
case class S(count: Int)
object S {
	implicit val SSemigroup: Semigroup[S] = new Semigroup[S] {
		override def combine(x: S, y:S): S = S(x.count+ y.count)
	}
}

//Monoid - Semigroup + has an identity element => Every Monoid is a Semigroup
case class M(count: Int) 
object M { //semigroup because it has an implicit monoid in it.
	implicit val AMonoid: Monoid[M] = new Monoid[M] {
	override def empty: M = M(0) //identity element
	override def combine(x: M, y: M) = M(x.count + y.count) //operation
	}
}

//M(1).combine(M(2) 
//M(1) |+| M(2)


//A Semigroup of functions
type FuncType = Int => Int
case class F(f: FuncType)
object F {
	implicit val FSemigroup: Semigroup[F] = new Semigroup[F] {
		override def combine(x: F, y: F): F = {
			F((k: Int) => x.f(y.f(k)))
		}
	}
}


//F(_*2).combine(F(_+10))(10)


Semigroup[Int ⇒ Int].combine({ (x: Int) ⇒x + 1}, { (x: Int) ⇒x * 10})
case class F(f: FuncType)
object F {
implicit val FSemigroup: Semigroup[F] = new Semigroup[F] {
override def combine(x: F, y: F): F = {
F((k: Int) => x.f(y.f(k)))
}
}
}*/
