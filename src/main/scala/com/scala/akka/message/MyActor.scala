package com.scala.akka.message

import akka.actor.{Actor, ActorRef}

class MyActor extends Actor{
  override def receive: Receive = {
    case (msg: String, actorRef: ActorRef) => actorRef ! msg
    case msg => println(msg)
  }
}
