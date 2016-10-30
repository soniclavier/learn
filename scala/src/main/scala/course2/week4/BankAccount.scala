package course2.week4

import course2.week4.frp.Var

/**
  * Created by vishnu on 10/29/16.
  */
class BankAccount extends Publisher {

  var balance = Var(0)
  def currentBalance: Int = balance()

  def deposit(amount: Int): Unit =
    if (amount > 0) {
      val b = balance()
      balance() = b + amount
    }

  def withdraw(amount: Int): Unit =
    if (amount > 0 && amount <= balance()) {
      val b = balance()
      balance() = b - amount
    }
    else
      throw new Error("insufficient funds")
}
