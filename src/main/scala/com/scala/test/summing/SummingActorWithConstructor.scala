package com.scala.test.summing

import akka.actor.Actor

class SummingActorWithConstructor (initialSum : Int) extends Actor {
  var sum = 0

  override def receive: Receive = {
    case x: Int => sum = sum + initialSum + x
      println(s"my state as sum is $sum")
    case _ => println("another")
  }
}