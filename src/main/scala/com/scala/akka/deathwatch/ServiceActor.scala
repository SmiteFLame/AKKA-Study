package com.scala.akka.deathwatch

import akka.actor.Actor

case object Service
case object Kill

class ServiceActor extends Actor{
  override def receive: Receive = {
    case Service => println("I provide a special service")
  }
}
