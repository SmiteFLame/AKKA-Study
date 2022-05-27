package com.scala.test.strategy

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, OneForOneStrategy}

import scala.concurrent.duration.DurationInt

class SupervisorStrategy extends Actor{
  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute){
    case _ : ArithmeticException => Restart
    case _ : NullPointerException => Resume
    case _ : IllegalArgumentException => Stop
    case _ : Exception => Escalate
  }
  val printer = context.
}
