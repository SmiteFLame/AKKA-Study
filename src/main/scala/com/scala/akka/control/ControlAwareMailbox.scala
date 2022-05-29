package com.scala.akka.control

import akka.dispatch.ControlMessage
import akka.actor.{Props, Actor, ActorSystem}

/**
 * Control Message: 트레이트
 * 트레이트를 확장하는 메시지는 ControlAwareMailBox가 우선적으로 다룬다.
 * ControlAwareMailBox는 두 개의 큐를 유지 해 ControlMessage를 확장하는 메시지가 우선으로 전달되도록 한다.
 */
case object MyControlMessage extends ControlMessage

object ControlAwareMailbox extends App {

  val actorSystem = ActorSystem("HelloAkka")
  val actor = actorSystem.actorOf(Props[Logger].withDispatcher(
    "control-aware-dispatcher"))

  actor ! "hello"
  actor ! "how are"
  actor ! "you?"

  /**
   * 이 Message가 먼저 들어오게 된다면 우선순위를 가장 먼저 지정하여 설정하게 된다.
   */
  actor ! MyControlMessage
}



