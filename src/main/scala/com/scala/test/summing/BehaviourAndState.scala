package com.scala.test.summing

import akka.actor.{ActorSystem, Props}

class BehaviourAndState extends App{
  val actorSystem = ActorSystem("HelloAkka")
  val actor = actorSystem.actorOf(Props[SummingActor])
  print(actor.path)

//  val actor2 = actorSystem.actorOf(Props(classOf[SummingActorWithConstructor, 10], "summingactor"))
//  print(actor2.path)
}
