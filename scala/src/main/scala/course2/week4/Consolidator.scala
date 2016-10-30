package course2.week4

/**
  * Created by vishnu on 10/29/16.
  */
class Consolidator(observed: List[BankAccount]) extends Subscriber {
  observed.foreach(_.subscribe(this))

  private var total: Int = _ //undersore indicates that variable in initially uninitialized
  compute()

  private def compute() =
    total = observed.map(_.currentBalance).sum

  def handler(pub: Publisher) = compute()

  def totalBalance = total
}
