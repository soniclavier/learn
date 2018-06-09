import cats._ , cats.instances._ , cats.implicits._


//Semigroup - An algebraic structure (a binary operation, a set)  which follows closure property & is associative
case class S(count: Int)
object S {
	implicit val SSemigroup: Semigroup[S] = new Semigroup[S] {
		override def combine(x: S, y:S): S = S(x.count+ y.count) //operation
	}
}

//Monoid - Semigroup + has an identity element => Every Monoid is a Semigroup
case class M(count: Int) 
object M { //also a semigroup because it has an implicit monoid in it.
	implicit val AMonoid: Monoid[M] = new Monoid[M] {
		override def empty: M = M(0) //identity element
		override def combine(x: M, y: M) = M(x.count + y.count) 
	}
}

//M(1).combine(M(2) 
//M(1) |+| M(2)

//A Semigroup of function
type FuncType = Int => Int
case class F(f: FuncType)
object F {
	implicit val FSemigroup: Semigroup[F] = new Semigroup[F] {
		override def combine(x: F, y: F): F = {
			F((k: Int) => x.f(y.f(k)))
		}
	}
}

//Semigroup[F].combine(F(_*2), F(_+10)).f(10)


//Applicative type [F] - has a method pure which lifts the value to the type F
Applicative[Option].pure(1)

//Monad = Applicative type + flatMap
//should follow:
	// 1. left unit law : pure(x) flatMap f == f(x)
	// 2. right unit law: mn flatMap pure == mn
	// 3. associative law: (a flatMap b) flatMap c == a flatMap (b flatMap c)

import scala.annotation.tailrec
case class MN[T](value :T)
object MN {
	implicit val MNMonad: Monad[MN] = new Monad[MN] {
	override def flatMap[A, B](fa: MN[A])(f: A => MN[B]): MN[B] = f(fa.value)
	override def pure[A](a: A): MN[A] = MN(a)
	@tailrec 
	override def tailRecM[A, B](a: A)(f: A => MN[Either[A,B]]): MN[B] = {
			f(a) match {
				case MN(Left(a)) => tailRecM(a)(f)
				case MN(Right(b)) => MN(b)
			}
		}
	}
}

//checking left unit law
val x = 23
val p = MN.MNMonad.pure(x)
def f(x: Int) = MN(x*2)
println(s"${p flatMap f} == ${f(x)}")
//MN(46) == MN(46)

//checking for right unit law
val mn = MN(x)
def pure(x: Int) = MN.MNMonad.pure(x)
println(s"${mn flatMap pure} == ${mn}")
//MN(23) == MN(23)

//checking for associativity
def f(x: Int) = MN(x * 2)
def g(x: Int) = MN(x + 2)
val mn = MN(x)
println(s"${(mn flatMap f) flatMap g} == ${mn flatMap (x => f(x) flatMap g)}")
//MN(48) == MN(48)

