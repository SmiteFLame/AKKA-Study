package com.scala.test.lifecycle

import akka.actor.Actor

case object Error

class LifeCycleActor extends Actor{
  var sum = 1

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println(s"sum in preRestart is $sum")
  }

  override def preStart(): Unit = {
    println(s"sum in preStart is $sum")
  }

  override def receive: Receive = {
    case Error => throw new ArithmeticException()
    case _ => println("default msg")
  }

  override def postStop(): Unit = {
    println(s"sum in postStop is ${sum * 3}")
  }

  override def postRestart(reason: Throwable): Unit = {
    sum = sum * 2
    println(s"sum in postRestart is $sum ")
  }
}
