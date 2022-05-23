package com.scala.test.become

import akka.actor.{Props, ActorSystem, Actor}

object BecomeUnBecomeApp extends App {

  val actorSystem = ActorSystem("HelloAkka")
  val becomeUnBecome = actorSystem.actorOf(Props[BecomeUnBecomeActor])

  /**
   * 현재 상태에 따라 데이터를 저장
   */

  becomeUnBecome ! 0.0 /* 맨 처음에는 receive으로 진행이 된다 */
  /**
   * 아래 부터는 상태가 변환되어 각각 진행을 하게 된다.
   */
  becomeUnBecome ! true /* 상태를 True로 저장 */
  becomeUnBecome ! "Hello how are you?" /* 문자열 출력 후 상태 저장*/
  becomeUnBecome ! false
  becomeUnBecome ! 1100
//  becomeUnBecome ! 0.0 /* 상태가 true로 바뀌였으므로 데이터를 출력하지 않음 */
  becomeUnBecome ! true
  becomeUnBecome ! "What do u do?"
}