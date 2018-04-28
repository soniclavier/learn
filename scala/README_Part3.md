<blockquote>Sep 2nd, 2016</blockquote>

## Streams
Streams are similar to lists, except that the tail is not evaluated until asked for.<br/>
The Stream would know the head, and would also know how to generate the tail.
```scala
 val xs = Stream.cons(1, Stream.cons(2, Stream.empty))   //> xs  : Stream.Cons[Int] = Stream(1, ?)
 val ys = List(1,2,3)                                    //> ys  : List[Int] = List(1, 2, 3)
```
Here there is a **?** for the tail of the Stream, indicating it is not evaluated. Whereas for a List, it is evaluated.

A collection such as a List or Set can be converted to stream by uinsg toStream method. e.g.,
```scala
List(1,2,3).toStream
//res1: scala.collection.immutable.Stream[Int] = Stream(1, ?)
```

we can get the head of the Stream by calling stream.head
```scala
val s = List(1,2,3,4,5,6,7,8,9,10).toStream
s.head
```
**take(n)** returns a Stream with n elements from the current stream
```scala
s.take(2)
```
**takeWhile()** returns the longest prefix of the stream till the predicte holds true
```scala
s.takeWhile(_<5)
```

### Concatinate Strams
**#::** is the Cons operator for Streams
```scala
0 #:: xs   //> res0: scala.collection.immutable.Stream[Int] = Stream(0, ?)
```
the cons method in Stream takes to parameters, head and tail. But the tail is a Name parameter(**called by name**, instead of value). Hence tail is not evaluted. Also, the tail is a `lazy` evaluated.


### Lazy evaluation
**val** : Evaluated when executed, evaluated only once
**lazy** : Evaluated only once and only when executed
**def** : Evaluated only when executed, but evaluted on each execution. (def defines a method)
[link1](http://stackoverflow.com/questions/4437373/use-of-def-val-and-var-in-scala/4440614#4440614) [link2](http://stackoverflow.com/questions/9449474/def-vs-val-vs-lazy-val-evaluation-in-scala)
[link3](http://stackoverflow.com/questions/11386559/val-mutable-versus-var-immutable-in-scala)

### Infinite Stream
Since tail of a Stream is evaluated lazily, we can have a function that creates an infinite Stream.<br/>

<blockquote>Apr 28th, 2018</blockquote>

## Cats (Category Theory)

```
libraryDependencies += "org.typelevel" %% "cats-core" % "1.1.0"
import cats._ , cats.instances._ , cats.implicits._
```
### Semigroup
An algebraic structure (a binary operation, a set)  which follows closure property & is associative
```scala
case class S(count: Int)
object S {
	implicit val SSemigroup: Semigroup[S] = new Semigroup[S] {
		override def combine(x: S, y:S): S = S(x.count+ y.count) //operation
	}
}
```
### Monoid
Semigroup + has an identity element => Every Monoid is a Semigroup

```scala
case class M(count: Int) 
object M { //also a semigroup because it has an implicit monoid in it.
	implicit val AMonoid: Monoid[M] = new Monoid[M] {
		override def empty: M = M(0) //identity element
		override def combine(x: M, y: M) = M(x.count + y.count) 
	}
}

//M(1).combine(M(2) 
//M(1) |+| M(2)
```
**A Semigroup of function**

```scala
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
```

### Applicative type [F] - 
has a method pure which lifts the value to the type F
```scala
Applicative[Option].pure(1)
```

### Monad
Applicative type + flatMap
should follow following rules:
	1. left unit law : pure(x) flatMap f == f(x)
	2. right unit law: mn flatMap pure == mn
	3. associative law: (a flatMap b) flatMap c == a flatMap (b flatMap c)

```scala
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
```
**checking left unit law**
```scala
val x = 23
val p = MN.MNMonad.pure(x)
def f(x: Int) = MN(x*2)
println(s"${p flatMap f} == ${f(x)}")
//MN(46) == MN(46)
```
**checking for right unit law**
```scala
val mn = MN(x)
def pure(x: Int) = MN.MNMonad.pure(x)
println(s"${mn flatMap pure} == ${mn}")
//MN(23) == MN(23)
```

**checking for associativity**
```scala
def f(x: Int) = MN(x * 2)
def g(x: Int) = MN(x + 2)
val mn = MN(x)
println(s"${(mn flatMap f) flatMap g} == ${mn flatMap (x => f(x) flatMap g)}")
//MN(48) == MN(48)
```
