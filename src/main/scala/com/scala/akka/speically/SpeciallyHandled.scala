package com.scala.akka.speically

import akka.actor.{ActorSystem, PoisonPill, Props}
import akka.routing.{Broadcast, RandomPool}

case object Handle

object SpeciallyHandled extends App {
  val actorSystem = ActorSystem("Hello-AKKA")
  val router = actorSystem.actorOf(RandomPool(5).props(Props[SpeciallyHandledActor]))
  router ! Broadcast(Handle)
  router ! Broadcast(PoisonPill)
  router ! Handle
}
