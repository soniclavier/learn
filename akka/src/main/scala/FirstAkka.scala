import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

object FirstAkka extends App {
  val system = ActorSystem("HelloSystem")
  val helloActor = system.actorOf(Props[HelloActor], name="helloactor")
  
  helloActor ! "hello"
  helloActor ! "bye"
}

class HelloActor extends Actor {
  def receive = {
    case "hello" => println("hello, how are you")
    case "bye"   => println("bye bye")
  }
}
