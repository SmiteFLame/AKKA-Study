package com.scala.test.parent

import akka.actor.Actor

case object CreateChild
case class Greet(msg: String)

class ChildActor extends Actor{
  override def receive: Receive = {
    case Greet(msg) => println(s"My parent[${self.path.parent}] greeted to me [${self.path} $msg")
  }
}
