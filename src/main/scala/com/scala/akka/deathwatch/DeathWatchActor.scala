package com.scala.akka.deathwatch

import akka.actor.{Actor, Props, Terminated}

class DeathWatchActor extends Actor{
  val child = context.actorOf(Props[ServiceActor], "serviceActor")
  context.watch(child)

  override def receive: Receive = {
    case Service => child ! Service
    case Kill =>
      context.stop(child)
    case Terminated(`child`) => println("The service actor has terminated and no longer available")
  }
}
