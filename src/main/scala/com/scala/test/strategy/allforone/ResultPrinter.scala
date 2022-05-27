package com.scala.test.strategy.allforone

import akka.actor.Actor

class ResultPrinter extends Actor{
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("Printer: I am restarting as well")
  }

  override def receive: Receive = {
    case msg => println("Receive Message: " + msg)
  }
}
