package com.scala.akka.strategy.oneforone

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, OneForOneStrategy, Props}

import scala.concurrent.duration.DurationInt

class SupervisorStrategy extends Actor{
  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute){
    case _ : ArithmeticException => Restart
    case _ : NullPointerException => Resume
    case _ : IllegalArgumentException => Stop
    case _ : Exception => Escalate
  }
  val printer = context.actorOf(Props[Printer])
  val intAdder = context.actorOf(Props[IntAdder])

  def receive = {
    /**
     * OneForOneStrategy 각각 독립적으로 일어나고 에러 처리도 각각 일어난다.
     */
    case "Start" => printer ! "Hello printer"
      printer ! 10
      intAdder ! 10
      intAdder ! 10
      intAdder ! "Hello int adder"
  }
}
