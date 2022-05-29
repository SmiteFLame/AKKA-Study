package com.scala.akka.message

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.dispatch.{Envelope, MailboxType, MessageQueue, ProducesMessageQueue}
import com.typesafe.config.{Config, ConfigFactory}

object CustomMailBox extends App {
  val actorSystem = ActorSystem("helloAkka")
  /**
   * Custom-dispatcher 를 통해서 어떤 사용자 메일 박스를 정의 한다.
   */
  val actor = actorSystem.actorOf(Props[MySpecialActor].withDispatcher("custom-dispatcher")) // MyMessageBox를 가져온다
  /**
   * MyActor 는 이제 MySpeicalActor와 소통을 진행한다.
   * MyActor는 actor1, actor2 두 인스턴스를 가지게 된다.
   * 이는 MySpeicalActor와 소통을 진행한다.
   */
  val actor1 = actorSystem.actorOf(Props[MyActor], "xyz")
  val actor2 = actorSystem.actorOf(Props[MyActor], "MyActor")
//  actor1 ! ("hello", actor) // MyMessageQueue로 스래드가 생성이 되고 xyz가 전달이된다.
  /**
   * 1. MyMessageQueue로 스래드가 생성이 되고 MyActor가 전달이 된다.
   * 2. MyMessageQueue의 queue에 쌓는다.
   * 3. MyMessageQueue를 통해서 이름이 MyActor인 내용과 소통을 진행한다.
   */
  actor2 ! ("hello", actor)
}
