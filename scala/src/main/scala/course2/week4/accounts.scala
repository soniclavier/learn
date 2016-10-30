package course2.week4

import course2.week4.frp.Signal

/**
  * Created by vishnu on 10/29/16.
  */
object accounts {
  def consolidated(accts: List[BankAccount]): Signal[Int] = {
    Signal(accts.map(_.balance()).sum)
  }
}
