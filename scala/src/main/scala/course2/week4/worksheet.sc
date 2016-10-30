import course2.week4.{Consolidator, BankAccount}

object observers {
  val a = new BankAccount
  val b = new BankAccount
  val c = new Consolidator(List(a, b))
  c.totalBalance

  a.deposit(20)
  c.totalBalance

}
