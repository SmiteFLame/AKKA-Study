package com.scala.akka.route.smallestmailbox

import akka.actor.{ActorSystem, Props}
import akka.routing.SmallestMailboxPool

object SmallestMailBox extends App {
  val actorSystem = ActorSystem("Hello-AKKA")
  /**
   * 메일 박스에 있는 메시지가 가장 적은 순서대로 들어간다
   */
  val router = actorSystem.actorOf(SmallestMailboxPool(5).props(Props[SmallestMailBoxActor]))
  for(i <- 1 to 5){
    router ! s"Hello $i"
  }
}
