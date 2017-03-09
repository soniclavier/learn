def tail(as: List[Int]): List[Int] = {
	as match {
		case Cons(x: xs) => xs
	}
}

def setHead(l: List[Int], head: Int): List[Int] = {
	l match {
		case Nil => Nil
		case x::xs => head::xs
	}
}

def drop[A](l: List[A], n: Int): List[A] = {
	l match {
		case x::xs if n > 0 => drop(xs, n - 1)
		case _ => l
	}
}


def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
	l match {
		case x::xs if f(x) => dropWhile(xs, f)
		case _ => l
	}
}

def init[A](l: List[A]): List[A] = {
	l match {
		case Nil => Nil
		case x::Nil => Nil
		case x::xs => x::init(xs)
	}
}

def foldRight[A, B](l: List[A], z: B)(f: (A, B) => B): B = {
	l match {
		case Nil => z
		case x::xs => f(x, foldRight(xs, z)(f))
	}
}

def product(l: List[Double]): Double = {
	foldRight(l, 1.0)(_ * _)
}

def length[A](l: List[A]): Int = {
	foldRight(l, 0)((x,y) => 1 + y)
}

def foldLeft[A, B](l: List[A], z: B)(f: (A, B) => B): B = {
	l match  {
		case x::xs => foldLeft(xs, f(x, z))(f)
		case Nil => z
	}
}

def sum2[Int](l: List[Int]): Int = {
	foldLeft(l, 0)(_ + _)
}

def product2(l: List[Double]): Double = {
	foldLeft(l, 1.0)(_ * _)
}

def length2[A](l: List[A]): Int = {
	foldLeft(l, 0)((x, y) => 1 + x)
}

def reverse[A](l: List[A]): List[A] = {
	foldLeft(l, Nil: List[A])((x, y) => x::y)
}

def append[A](l: List[A], b: A): List[A] = {
	foldLeft(l, List(b))((x,y) => x::y)
}

def flattenList[A](l: List[List[A]]): List[A] = {
	foldLeft(l, Nil: List[A])((xs, sl) => {foldLeft(sl, xs)((ssl, e) => ssl::e)})
}

def add1(l: List[Int]): List[Int] = {
	foldRight(l, Nil: List[Int])((e, xs) => (e+1)::xs)
}

def doubleToString(l: List[Double]): List[String] = {
	foldRight(l, Nil: List[String])((e, xs) => e.toString::xs)
}

def map[A, B](as: List[A])(f: A => B): List[B] = {
	foldRight(as, Nil: List[B])((a, l) => f(a)::l)
}

def filter[A](as: List[A])(f: A => Boolean): List[A] = {
	as match {
		case (h::t) if (f(h)) => h::filter(t)(f)
		case (h::t) => filter(t)(f)
		case Nil => Nil
	}
}

def filter[A](as: List[A])(f: A => Boolean): List[A] = {
	foldRight(as, Nil: List[A])((e, xs) => if (f(e)) e::xs else xs)
}

def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] = {
	foldRight(as, Nil: List[B])((e, xs) => foldRight(f(e), xs)((ee, xxs) => ee::xxs))
}

def filter2[A](as: List[A])(f: A => Boolean): List[A] = {
	flatMap(as)(e => if(f(e)) List(e) else Nil)
}

def addLists(as: List[Int], bs: List[Int]): List[Int] = (as, bs) match {
	case (Nil, _) => Nil
	case (_, Nil) => Nil
	case (a::at, b::bt) => a+b::addLists(at, bt)
}

def zipWith[A, B, C](as: List[A], bs: List[B])(f: (A, B) => C): List[C] = (as, bs) match {
	case (Nil, _) => Nil
	case (_, Nil) => Nil
	case (a::at, b::bt) => f(a,b)::zipWith(at, bt)(f)
}

def hasSubsequence[A](l: List[A], s: List[A]): Boolean = (l, s) match {
	case (_, Nil) => true
	case (Nil, _) => false
	case (a::at, b::bt) => if (a == b) hasSubsequence(at, bt) else hasSubsequence(at, b::bt)
}

//trees
sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

def size[A](t: Tree[A]): Int = {
	t match {
		case Leaf(_) => 1
		case Branch(l, r) => 1 + size(l) + size(r)
	}
}

def maximum(t: Tree[Int]): Int = t match {
	case Leaf(v) => v
	case Branch(l, r) => maximum(l) max maximum(r)
}

def depth[A](t: Tree[A]): Int = t match {
	case Leaf(_) => 0
	case Branch(l, r) => 1 + (depth(l) max depth(r))
}

def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
	case Leaf(v) => Leaf(f(v))
	case Branch(l, r) => Branch(map(l)(f), map(r)(f))
}


def fold[A, B](t: Tree[A])(g: A => B)(f: (B, B) => B): B = t match {
	case Leaf(v) => g(v)
	case Branch(l, r) => f(fold(l)(g)(f), fold(r)(g)(f))
}

def size2[A](t: Tree[A]): Int = {
	fold(t)(a => 1)((l, r) => (1 + l + r))
}

def maximum2[A](t: Tree[Int]): Int = {
	fold(t)(a => a)(math.max(_, _))
}

def depth2[A](t: Tree[A]): Int = {
	fold(t)(a => 1)((l, r) => 1 + (l max r))
}

def map2[A, B](t: Tree[A])(f: A => B): Tree[B] = {
	fold(t)(v => Leaf(f(v)): Tree[B])((l, r) => Branch(l, r))
}

//chapter 4, Option, Either
import scala.{Option => _, Either => _, _}
trait Option[+A] {
	def map[B](f: A => B): Option[B] = this match {
		case Some(v) => Some(f(v))
		case None => None
	}
	def flatMap[B](f: A => Option[B]): Option[B] = {
		map(f) getOrElse None
	}
	def getOrElse[B >: A](default: => B): B = this match {
		case Some(v) => v
		case None => default
	}
	def orElse[B >: A](ob: => Option[B]): Option[B] = {
		this map(Some(_)) getOrElse ob
	}
	def filter(f: A => Boolean): Option[A] = {
		flatMap(a => if (f(a)) Some(a) else None)
	}
}

case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

import scala.util.{Success, Try}

def mean(xs: Seq[Double]): Option[Double] = {
	val t = xs.foldLeft(0.0, 1)((acc, b) => a._1 + b, a._2 + 1)
	t._1/t._2
}

def variance(xs: Seq[Double]): Option[Double] = {
	mean(xs) flatMap(m => mean(xs map(x => math.pow(x - m, 2))))
}

def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = (a, b) match {
	case (Some(a), Some(b)) => Some(f(a,b))
	case _ => None
}

def sequence[A](a: List[Option[A]]): Option[List[A]] = {
	a match {
		case h::t => h.flatMap(e => sequence(t).map(ee => e::ee))
		case Nil => Some(Nil)
	}
}

def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = {
	a match {
		case h::t => f(h).flatMap(e => traverse(t)(f).map(ee => e::ee))
		case Nil => Some(Nil)
	}
}

def sequence2[A](a: List[Option[A]]): Option[List[A]] = {
	traverse(a)(s => s)
}

//Either
sealed trait Either[+E, +A] {
	def map[B](f: A => B): Either[E, B] = {
		this match {
			case Right(v) => Right(f(v))
			case Left(v) => Left(v)
		}
	}

	def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = {
		/*
		map(f) orElse {this match {
			case Left(v) => Left(v)
			}}*/
		this match {
			case Right(v) => f(v)
			case Left(v) => Left(v)
		}
	}

	def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = {
		this match {
			case Right(v) => Right(v)
			case Left(v) => b
		}
	}

	def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => Either[EE, C]): Either[EE, C] = {
		//http://codereview.stackexchange.com/questions/40130/implementing-optionmap2
		(b, this) match {
			case (Right(v1), Right(v2)) => f(v2, v1)
			case (_, Left(v)) => Left(v)
			case (Left(v), _) => Left(v)

		}
		/*
		for {
			aa <- this
			bb <- b
		} yield f(aa, bb)
		*/
		/*how does for-comprehension check for None and terminate(what is the equaivalen expansion)
		http://stackoverflow.com/questions/14598990/confused-with-the-for-comprehension-to-flatmap-map-transformation
		for comprehension is a syntax shortcut to combine flatMap and map
		Each line in the expression using the <- symbol is translated to a flatMap call, except for the last line which is translated to a concluding map
		e.g., 
		case(1)
		val m: Option[Int] = Some(25)
		val k: Option[Int] = Some(10)
		def f(a: Int, b: Int) = (a*b)/(a+b)
		for {
			kk <- k
     		mm <- m
     	} yield f(kk,mm)
     	returns Some(7)
     	case(2)
     	val m: Option[Int] = None 
		//now the for-comprehension will return None
		if 
		case(3)
		val k: Option[Int] = None
		val m: Option[Int] = Some(25)
		//again the for-comprehension will return None

		the expanded version of for comprehension is
		k.flatMap(kk => m.map(mm => f(kk,mm)))
		to illustrate what is going on
		k.flatMap(kk => {
			println("in flatMap with kk = "+kk)
			m.map(mm => {
				println("in map with mm = "+mm)
				f(kk,mm)
			})
		})

		case(1) output :
			Option: in flatmap
			Option: in map
			Option: applying f on 10
			in flatMap with kk = 10
			Option: in map
			Option: applying f on 25
			in map with mm = 25
			res2: Option[Int] = Some(7)
			went through both map and flatMap and applied f
		case(2) output :
			Option: in flatmap
			Option: in map
			Option: applying f on 10
			in flatMap with kk = 10
			Option: in map
			Option: returning None from map
			res3: Option[Int] = None
			went through k, but didnt go through m.map since m is None
		case(3) output:
			Option: in flatmap
			Option: in map
			Option: returning None from map
			Option: returning None from flatMap
			res4: Option[Int] = None
			didnt even go through k.flatMap since k is None
		*/
	}
}
case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]

//List
import scala.{List => _}
import java.lang.Exception
sealed trait List[+A] {
	def isEmpty: Boolean
	def head: A
	def tail: List[A]
	def length: Int	
	def last: A
	def init: List[A]
	def take(n: Int): List[A]
	def drop(n: Int): List[A]
	def apply(n: Int): A
}
case object Nil extends List[Nothing] {
	def isEmpty = true
	def head = throw new Exception("Head of empty list")
	def tail = throw new Exception("Tail of empty list")
	def length = 0
	def last = throw new Exception("Last of empty list")
	def init = throw new Exception("Init of empty list")
	def take(n: Int) = Nil
	def drop(n: Int) = Nil
	def apply(n: Int) = throw new Exception("No such element exception")
}
case class Cons[A](val head: A, val tail: List[A]) extends List[A] {
	def isEmpty = false
	def length = 1 + tail.length
	def last = tail match {
		case Nil => head
		case _ => tail.last
	}
	def init = tail match {
		case Cons(thead, Nil) => Cons(head, Nil)
		case Cons(thead, ttail) => Cons(head, tail.init)
		case _ => Nil
	}
	def take(n: Int): List[A] = this match {
		case Cons(head, tail) if n > 0 => Cons(head, tail.take(n-1))
		case _ => Nil
	}
	def drop(n: Int): List[A] = this match {
		case Cons(head, tail) if n > 0 => tail.drop(n-1)
		case _ => this
	}
	def apply(n: Int): A = this match {
		case Cons(head, tail) if n == 0 => head
		case Cons(head, tail) if n > 0 => tail(n - 1)
		case _ => throw new Exception("no such element exception")
	}
}

//chap 6
trait RNG {
	def nextInt: (Int, RNG)
}
case class SimpleRNG(seed: Long) extends RNG {
	def nextInt: (Int, RNG) = {
		val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
		val nextRNG = SimpleRNG(newSeed)
		val n = (newSeed >>> 16).toInt
		(n, nextRNG)
	}
}


def nonNegativeInt(rng: RNG): (Int, RNG) = {
	val (n, rng2) = rng.nextInt
	(if (n < 0) -(n + 1) else n, rng2)
}

def double(rng: RNG): (Double, RNG) = {
	val (n, rng2) = nonNegativeInt(rng)
	(n / (Int.MaxValue.toDouble + 1), rng2)
}

def intDouble(rng: RNG): ((Int, Double), RNG) = {
	val (n, rng2) = rng.nextInt
	val (d, rng3) = double(rng2)
	((n, d), rng3)
}

def doubleInt(rng: RNG): ((Double, Int), RNG) = {
	val ((n, d), rng2) = intDouble(rng)
	((d, n), rng2) 
}

def ints(count: Int)(rng: RNG): (List[Int], RNG) = {
	if (count > 0) {
		val (i, r) = rng.nextInt
		val (l, r2) = ints(count - 1)(r)
		(i :: l, r2)
	} else {
		(List(), rng)
	}
}

//tail recursive version
def ints2(count: Int)(rng: RNG): (List[Int], RNG) = {
	def intsHelper(count: Int, r: RNG, ls: List[Int]): (List[Int], RNG) = {
		if (count > 0) {
			val (i, r2) = r.nextInt
			intsHelper(count - 1, r2, i::ls)
		} else {
			(ls, r)
		}
	}
	intsHelper(count)(rng)
}


type Rand[+A] = RNG => (A, RNG)

val int: Rand[Int] = _.nextInt

def unit[A](a: A): Rand[A] = rng => (a, rng)

def map[A, B](s: Rand[A])(f: A => B): Rand[B] = {
	rng => {
		val (i, r) = s(rng)
		(f(i), r)
	}
}

def nonNegativeEven: Rand[Int]  = {
	map(nonNegativeInt)(i => i/2)
}

def double2(rng: RNG): (Double, RNG) = {
	map(nonNegativeInt)(n => n / (Int.MaxValue.toDouble + 1))(rng)
}

def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
	rng => {
		val (i1, r1) = ra(rng)
		val (i2, r2) = rb(r1)
		(f(i1, i2), r2)
	}
}

def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = {
	def sequenceHelper(fs2: List[Rand[A]])(rng: RNG): (List[A], RNG) = fs2 match {
		case h::t => {
			val (i, r2) = h(rng)
			val (l, rn) = sequenceHelper(t)(r2)
			((i::l), rn)
		}
		case Nil => (List[A](), rng)
	}
	rng => sequenceHelper(fs)(rng)
}

def sequenceUsingFold[A](fs: List[Rand[A]]): Rand[List[A]] = {
	fs.foldLeft(unit(List[A]()))((f, acc) => map2(f, acc)(_ :: _))
}

