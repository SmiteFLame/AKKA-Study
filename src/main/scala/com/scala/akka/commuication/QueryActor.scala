package com.scala.akka.commuication

import akka.actor.Actor

class QueryActor extends Actor{
  import Message._

  override def receive: Receive = {
    case Start(actorRef) => println(s" send me the next random number")
      actorRef ! GiveRandomNumber
    case Done(randomNumber) =>
      println(s"receiced a random number $randomNumber")
  }
}
