package week4

abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat
  def +(that: Nat):Nat
  def -(that: Nat):Nat
}

object Zero extends Nat {
  def isZero: Boolean = true
  def predecessor:Nat = throw new Exception("Number is zero")
  def successor:Nat = new Succ(this)
  def +(that:Nat):Nat = that
  def -(that:Nat):Nat = if (that.isZero) this else throw new Exception("Number is negative")
}

class Succ(n:Nat) extends Nat {
  def isZero = false
  def predecessor = n
  def successor = new Succ(this)
  def +(that:Nat) = new Succ(n+that)
  def -(that:Nat) = if (that.isZero) this else n - that.predecessor
}