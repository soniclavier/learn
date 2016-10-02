package chatroom

import akka.actor.{Actor, ActorRef}

/**
  * Created by vishnu on 10/2/16.
  */
class User(chatRoom: ActorRef) extends Actor {
  import User._

  override def receive: Receive = {
    case Connected(outgoing) =>
      context.become(connected(outgoing))
  }

  def connected(outgoing: ActorRef): Receive = {
    chatRoom ! ChatRoom.Join

    {
      case IncomingMessage(text) =>
        chatRoom ! ChatRoom.ChatMessage(text)

      case ChatRoom.ChatMessage(text) =>
        outgoing ! OutgoingMessage(text)
    }
  }
}


object User {
  case class Connected(outgoing: ActorRef)
  case class IncomingMessage(text: String)
  case class OutgoingMessage(text: String)
}