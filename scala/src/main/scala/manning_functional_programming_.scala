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
