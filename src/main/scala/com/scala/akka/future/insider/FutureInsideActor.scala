package com.scala.akka.future.insider

import akka.actor.{ActorSystem, Props}

object FutureInsideActor extends App {
  val actorSystem = ActorSystem("Hello-Akka")
  val fActor = actorSystem.actorOf(Props[FutureActor])
  fActor ! (10, 20)
}
