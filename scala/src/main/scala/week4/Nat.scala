package week4

abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat
  def +(that: Nat):Nat
  def -(that: Nat):Nat
}

object Zero extends Nat {
  def isZero = true
  def predecessor = throw new Exception("no predecessor for zero")
  def successor = new Succ(this)
  def +(that: Nat) = that
  def -(that: Nat) = if (that.isZero) this else throw new Exception("cannot subtract from zero")
}

class Succ(n:Nat) extends Nat {
  def isZero = false
  def predecessor = n
  def successor = new Succ(this)
  def +(that: Nat) = new Succ(n + that)
  def -(that: Nat) = if (that.isZero) this else n - that.predecessor 
}