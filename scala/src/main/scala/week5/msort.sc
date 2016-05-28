package week5

object msort {
  println("Welcome to the Scala worksheet")

  def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[T], ys: List[T]): List[T] = {
        (xs, ys) match {
          case (Nil, ys) => ys
          case (xs, Nil) => xs
          case (x :: xs1, y :: ys1) =>
            if (ord.lt(x, y)) x :: merge(xs1, ys)
            else y :: merge(xs, ys1)
        }
      }
      val (fst, lst) = xs.splitAt(n)
      merge(msort(fst), msort(lst))

    }
  }

  val nums = List(1, 2, 0, -2, 5)
  msort(nums)

  case class Book(title: String, authors: List[String])

  val books: List[Book] = List(Book("book1", List("auth1")), Book("book2", List("auth1", "auth2")), Book("book3", List("auth1")))

  for (b <- books; a <- b.authors if a startsWith "auth2")
    yield b.title

  for (b <- books if b.title contains "1")
    yield b.title

  for {
    b1 <- books
    b2 <- books
    if b1.title < b2.title
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1
}