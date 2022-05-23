package com.scala.test.commuication

import akka.actor.{ActorSystem, Props}

object Communication extends App {
  import Message._
  val actorSystem = ActorSystem("HelloAkka")
  val randomNumberGenerator = actorSystem.actorOf(Props[RandomNumberGeneratorActor], "randomNumberGeneratorActor")
  val queryActor = actorSystem.actorOf(Props[QueryActor], "queryActor")
  queryActor ! Start(randomNumberGenerator)
}
