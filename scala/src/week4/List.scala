package week4

import java.util.NoSuchElementException

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Cons[T](val head:T,val tail: List[T]) extends List[T] {
  def isEmpty = false
  //head and tail is already there in value parameters, so that it will act as the implementation
  //for head, and tail
  
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.head")
  
  def myfun[T](name: T) : T = {
    return name
  }
}

object List {
  def apply[T](x1:T,x2:T) : List[T] = new Cons(x1, new Cons(x2, new Nil))
  def apply[T]() = new Nil  
}

