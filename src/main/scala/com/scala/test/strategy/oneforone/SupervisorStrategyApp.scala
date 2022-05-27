package com.scala.test.strategy.oneforone

import akka.actor.{ActorSystem, Props}

object SupervisorStrategyApp extends App {
  val actorSystem = ActorSystem("Supervision")
  actorSystem.actorOf(Props[SupervisorStrategy]) ! "Start"
}
