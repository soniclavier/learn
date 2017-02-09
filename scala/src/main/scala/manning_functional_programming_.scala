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