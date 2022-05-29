package com.scala.akka.strategy.allforone

import akka.actor.{ActorSystem, Props}

object AllForOneStrategyApp extends App {
  val system = ActorSystem("Hello-Akka")
  val supervisor = system.actorOf(Props[AllForOneStrategySupervisor], "supervisor")
  supervisor ! "Start"
}
