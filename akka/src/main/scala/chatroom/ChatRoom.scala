package chatroom

import akka.actor.Actor.Receive
import akka.actor.{Terminated, ActorRef, Actor}

/**
  * Created by vishnu on 10/2/16.
  *
  * from : https://markatta.com/codemonkey/blog/2016/04/17/chat-with-akka-http-websockets/
  */
class ChatRoom extends Actor{
  import ChatRoom._
  var users: Set[ActorRef] = Set.empty

  override def receive: Receive = {
    case Join =>
      users += sender()
      context.watch(sender())

    case Terminated(user) =>
      users -= user
    case msg: ChatMessage =>
      users.foreach(_ ! msg)
  }
}


object ChatRoom {
  case object Join
  case class ChatMessage(message: String)
}
