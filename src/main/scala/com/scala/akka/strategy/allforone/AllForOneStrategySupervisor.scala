package com.scala.akka.strategy.allforone

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, AllForOneStrategy, Props}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class AllForOneStrategySupervisor extends Actor{
  override val supervisorStrategy = AllForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 seconds){
    case _: ArithmeticException => Restart
    case _: NullPointerException => Resume
    case _: IllegalArgumentException => Stop
    case _: Exception => Escalate
  }

  /**
   * Supervisor 감독하에 두개의 액터를 만든다
   * 감독하에 연산결과를 모두 Result Printer로 송신한다는 측면에서 긴밀한 관계를 가진다.
   */
  val printer = context.actorOf(Props[ResultPrinter])
  val calculator = context.actorOf(Props(classOf[Calculator], printer))

  override def receive: Receive = {
    case "Start" => calculator ! Add(10, 12)
      calculator ! Sub(12, 10)
      calculator ! Div(5, 2)
      calculator ! Div(5, 0)
  }
}
