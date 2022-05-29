package com.scala.akka.parent

import akka.actor.{Actor, Props}

class ParentActor extends Actor {
  override def receive: Receive = {
    case CreateChild =>
      val child = context.actorOf(Props[ChildActor], "child")
      child ! Greet("Hello Child")
  }
}
