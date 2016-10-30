package course2.week4

/**
  * Created by vishnu on 10/29/16.
  */
trait Subscriber {
  def handler(pub: Publisher)
}
