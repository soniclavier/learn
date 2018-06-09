import cats.Monad
import scala.annotation.tailrec

case class MN[T](value :T)

object MN {
  implicit val MNMonad: Monad[MN] = new Monad[MN] {
    override def flatMap[A, B](fa: MN[A])(f: A => MN[B]): MN[B] = {
      f(fa.value)
    }
    override def pure[A](a: A): MN[A] = MN(a)
    @tailrec
    override def tailRecM[A, B](a: A)(f: A => MN[Either[A,B]]): MN[B] = {
      f(a) match {
        case MN(Left(nextVal)) => tailRecM(nextVal)(f)
        case MN(Right(res)) => MN(res)
      }
    }
  }
}

class MonadTest {
  def main(args: Array[String]): Unit = {
    val k = MN(10)

  }
}
