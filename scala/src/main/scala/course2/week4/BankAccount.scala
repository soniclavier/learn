package course2.week4

/**
  * Created by vishnu on 10/29/16.
  */
class BankAccount extends Publisher {

  private var balance = 0
  def currentBalance: Int = balance

  def deposit(amount: Int): Unit =
    if (amount > 0) {
      balance += amount
      publish()
    }

  def withdraw(amount: Int): Unit =
    if (amount > 0 && amount <= balance) {
      balance -= amount
      publish()
    }
    else
      throw new Error("insufficient funds")
}
