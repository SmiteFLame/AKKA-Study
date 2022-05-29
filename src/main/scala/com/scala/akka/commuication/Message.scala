package com.scala.akka.commuication

import akka.actor.ActorRef

object Message {
  case class Done(randomNumber: Int)
  case object GiveRandomNumber
  case class Start(actorRef: ActorRef)
}
