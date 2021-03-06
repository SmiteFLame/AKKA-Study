package com.scala.akka.persistence.shutdown

import akka.actor.{ActorSystem, PoisonPill, Props}

object SafePersistenceActorShutdownApp extends App {
  val system = ActorSystem("safe-shutdown")
  val persistentActor1 = system.actorOf(Props[SamplePersistenceActor])
  val persistentActor2 = system.actorOf(Props[SamplePersistenceActor])

  persistentActor1 ! UserUpdate("foo", Add)
  persistentActor1 ! UserUpdate("foo", Add)

  /**
   * PosionPill 과 사용자 정의 멧지의 행위를 별도로 확인 할 수 있다.
   */
  persistentActor1 ! PoisonPill
  persistentActor2 ! UserUpdate("foo", Add)
  persistentActor2 ! UserUpdate("foo", Add)
  persistentActor2 ! ShutdownPersistentActor
}
