package com.scala.akka.lifecycle

import akka.actor.SupervisorStrategy.{Escalate, Restart}
import akka.actor.{Actor, ActorRef, OneForOneStrategy, Props}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

case class StopActor(actorRef: ActorRef)

class Supervisor extends Actor{
  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute){
    case _ : ArithmeticException => Restart
    case t => super.supervisorStrategy.decider.applyOrElse(t, (_: Any) => Escalate)
  }

  override def receive: Receive = {
    case (props: Props, name: String) =>
      sender ! context.actorOf(props, name)
    case StopActor(actorRef) => context.stop(actorRef)
  }
}
