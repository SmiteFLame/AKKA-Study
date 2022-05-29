package com.scala.akka.send

import akka.actor.Actor

case class DoubleValue(x: Int)
case object CreateChild
case object Send
case class Response(x: Int)

class DoubleActor extends Actor{
  override def receive: Receive = {
    case DoubleValue(number) =>
      println(s"${self.path.name} Got the number $number")
      sender ! Response(number * 2)
  }
}
