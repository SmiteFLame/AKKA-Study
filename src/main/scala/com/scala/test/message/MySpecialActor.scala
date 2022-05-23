package com.scala.test.message

import akka.actor.Actor

class MySpecialActor extends Actor {
  override def receive: Receive = {
    case msg: String => println(s"msg is $msg")
  }
}
