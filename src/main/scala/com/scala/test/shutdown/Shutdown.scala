package com.scala.test.shutdown

import akka.actor.{ActorSystem, PoisonPill, Props}


object ShutdownApp extends App {
  val actorSystem = ActorSystem("HelloAkka")
  val shutdownActor1 = actorSystem.actorOf(Props[ShutdownActor], "shutdownActor1")
  shutdownActor1 ! "hello"

  /**
   * PoisonPill: 메일 박스에 이미 모든 메시지 이후에 처리되는 내장 메시지
   */
  shutdownActor1 ! PoisonPill
  shutdownActor1 ! "Are you there?"

  val shutdownActor2 = actorSystem.actorOf(Props[ShutdownActor], "shutdownActor2")
  shutdownActor2 ! "hello"

  /**
   * context.stop: 액터를 순간적으로 정지시킬때 사용된다. 자식 액터부터 중단시키고, 부모 액터, 액터 시스템 순서로 진행해 최 상위 액터를 중단시킨다.
   */
  shutdownActor2 ! Stop
  shutdownActor2 ! "Are you there?"

}