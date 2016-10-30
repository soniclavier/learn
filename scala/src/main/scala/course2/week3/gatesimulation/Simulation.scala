package course2.week3.gatesimulation

trait Simulation {
  type Action = () => Unit
  
  case class Event(time: Int, action: Action)
  
  private var currTime = 0
  def currentTime: Int = currTime
  
  private type Agenda = List[Event]
  private var agenda: Agenda = List()
  
  private def insert(ag: Agenda, item: Event): Agenda = ag match {
    case first :: rest if first.time <= item.time => first :: insert(rest, item)
    case _ => item :: ag
  }
  
  def afterDelay(delay: Int)(block: => Unit): Unit = {
    val item = Event(currentTime + delay, () => block)
    agenda = insert(agenda, item)
  }
  
  def run() {
    afterDelay(0) {
      println(s"*** Simulation started at $currentTime ***")
    }
    loop()
  }
  
  private def loop(): Unit = agenda match {
    case first :: rest =>
      agenda = rest
      currTime = first.time
      first.action()
      loop()
    case _ => println(" no more events in the agenda")
  }
  
  
}