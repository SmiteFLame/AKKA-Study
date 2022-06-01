package com.scala.akka.route.resizable

import akka.actor.Actor

class LoadActor extends Actor{
  override def receive: Receive = {
    case Load => println("Handing loads of requests")
  }
}
