package com.scala.akka.smallestmailbox

import akka.actor.Actor

class SmallestMailBoxActor extends Actor{
  override def receive: Receive = {
    case msg: String => println(s"I am ${self.path.name}")
    case _ => println(s" I dont understand the message")
  }
}
