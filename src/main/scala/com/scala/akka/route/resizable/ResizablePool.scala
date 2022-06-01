package com.scala.akka.route.resizable

import akka.actor.{ActorSystem, Props}
import akka.routing.{DefaultResizer, RoundRobinPool}

case object Load

object ResizablePool extends App{
  val actorSystem = ActorSystem("hello-akka")
  val resizer = DefaultResizer(lowerBound = 2, upperBound = 15)
  val router = actorSystem.actorOf(RoundRobinPool(5, Some(resizer)).props(Props[LoadActor]))
  router ! Load
}
