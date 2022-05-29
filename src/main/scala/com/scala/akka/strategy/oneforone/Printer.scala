package com.scala.akka.strategy.oneforone

import akka.actor.Actor

class Printer extends Actor{
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("Printer: I am restarting because of ArithmeticException")
  }

  override def receive: Receive = {
    case msg: String => println(s"Printer $msg")
    case msg: Int => 1 / 0
  }
}
