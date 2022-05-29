package com.scala.akka

import akka.actor.{ActorSystem, Props}
import com.scala.akka.summing.{SummingActor, SummingActorWithConstructor}

object MainClass {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("HelloAkka")
    val actor = actorSystem.actorOf(Props[SummingActor])
    println(actor.path)

    val actor2 = actorSystem.actorOf(Props[SummingActor], "SummingActor")
    println(actor2.path)

    val actor3 = actorSystem.actorOf(Props(classOf[SummingActorWithConstructor], 10), "SummingActorWithConstructor")
    println(actor3.path)

    while (true){
      Thread.sleep(1000)
      actor ! 1
    }
  }
}
