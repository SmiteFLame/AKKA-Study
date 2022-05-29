package com.scala.akka.lifecycle

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object ActorLifeCycle extends App {
  implicit val timeout = Timeout(2 seconds)
  val actorSystem = ActorSystem("Supervision")
  val supervisor = actorSystem.actorOf(Props[Supervisor], "supervisor")
  val childFuture = supervisor ?  (Props(new LifeCycleActor), "LifeCycleActor")
  val child = Await.result(childFuture.mapTo[ActorRef], 2 seconds)
  child ! Error
  Thread.sleep(1000)
  supervisor ! StopActor(child)

  /**
   * 액터 호출 순서
   * 1. preStart가 가장 먼저 호출이 되고 출력이 된다.
   * 2. 액터가 예외를 던지면 메시지를 슈퍼 바이저에게 송신, 슈퍼 바이저는 액터를 재시작해 장애를 처리한다.
   * 3. 슈퍼바이저는 액터의 누적된 상태를 지우고, 새로운 액터를 만든다.
   * 4. 예전 액터에 할당 됐던 이전 값을 preStart값으로 복원 시킨다
   * 5. postRestart 메서드가 호출된 후, 언제든지 액터가 중단되면, 슈퍼바이저는 postStop을 호출한다.
   */
}
