package com.scala.akka.summing

import akka.actor.Actor

class SummingActor extends Actor {
  var sum = 0

  override def receive: Receive = {
    case x: Int => sum = sum + x
      println(s"my state as sum is $sum")
    case _ => println("another")
  }
}