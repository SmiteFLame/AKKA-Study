package com.scala.test.deathwatch

import akka.actor.{ActorSystem, Props}

object DeathWatchApp extends App {
  val actorSystem = ActorSystem("Supervision")
  val deathWatchActor = actorSystem.actorOf(Props[DeathWatchActor])
  deathWatchActor ! Service
  deathWatchActor ! Service
  Thread.sleep(1000)
  deathWatchActor ! Kill
  Thread.sleep(1000)
  deathWatchActor ! Service
}
