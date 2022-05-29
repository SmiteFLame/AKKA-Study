package com.scala.akka.priority

import akka.actor.{Props, ActorSystem, Actor}
import akka.dispatch.{PriorityGenerator, UnboundedPriorityMailbox}
import com.typesafe.config.Config

object PriorityMailBoxApp extends App {

  val actorSystem = ActorSystem("HelloAkka")

  /**
   * Int, Long, String 만 처리하는 액터
   */

  val myPriorityActor = actorSystem.actorOf(Props[MyPriorityActor].withDispatcher("prio-dispatcher"))

  /**
   * 문자열에 우선순위가 가장 높기 때문에 문자열이 가장 먼저 나오게 된다.
   */
  myPriorityActor ! 6.0
  myPriorityActor ! 6.1
  myPriorityActor ! 6.2
  myPriorityActor ! 6.3
  myPriorityActor ! 6.4
  Thread.sleep(3)
  myPriorityActor ! 6.5
  myPriorityActor ! 6.6
  myPriorityActor ! 1
  myPriorityActor ! 6.7
  myPriorityActor ! 3
  myPriorityActor ! "Hello"
  myPriorityActor ! 5
  myPriorityActor ! "I am priority actor"
  myPriorityActor ! "I process string messages first,then integer, long and others"

}

