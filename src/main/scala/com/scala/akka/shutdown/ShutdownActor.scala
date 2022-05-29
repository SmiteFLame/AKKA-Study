package com.scala.akka.shutdown

import akka.actor.Actor

case object Stop

class ShutdownActor extends Actor{
  override def receive: Receive = {
    case msg:String => println(s"$msg")
    case Stop => context.stop(self)
  }
}
