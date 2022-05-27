package com.scala.test.strategy.oneforone

import akka.actor.Actor

class IntAdder extends Actor{
  var x = 0

  override def receive: Receive = {
    case msg: Int => x = x + msg
      println(s"IntAdder : sum is $x")
    case msg: String => throw new IllegalArgumentException
  }

  override def postStop(): Unit = {
    println("IntAdder: I am getting stopped because I got a string message")
  }
}
