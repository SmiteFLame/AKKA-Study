package com.scala.test

import akka.actor.ActorSystem

class HelloAkkaActorSystem extends App {
  val actorSystem = ActorSystem("HelloAkka")
  print(actorSystem)
}

object HelloWorld {
  def main(args: Array[String]) {
    val actorSystem = ActorSystem("HelloAkka")
    print(actorSystem)
  }
}