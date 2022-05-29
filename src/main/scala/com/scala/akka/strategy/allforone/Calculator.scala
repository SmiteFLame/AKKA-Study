package com.scala.akka.strategy.allforone

import akka.actor.{Actor, ActorRef}

case class Add(a: Int, b: Int)
case class Sub(a: Int, b: Int)
case class Div(a: Int, b: Int)

class Calculator(printer: ActorRef) extends Actor{
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("Calculator: I am restarting because of ArithmeticException")
  }

  override def receive: Receive = {
    case Add(a, b) => printer ! s"sum is ${a + b}"
    case Sub(a, b) => printer ! s"sub is ${a - b}"
    case Div(a, b) => printer ! s"div is ${a / b}"
  }
}
