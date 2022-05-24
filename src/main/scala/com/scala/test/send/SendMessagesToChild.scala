package com.scala.test.send

import akka.actor.{ActorSystem, Props}

object SendMessagesToChild extends App {
  val actorSystem = ActorSystem("helloAkka")
  val parent = actorSystem.actorOf(Props[ParentActor], "parent")

  /**
   * 3개의 자식 액터를 만들고 CreateChild 메시지를 수신할 때 자식 액터를 만들어 리스트를 유지하는 부모 액터를 만들었다.
   */
  parent ! CreateChild
  parent ! CreateChild
  parent ! CreateChild

  /**
   * Send 메시지가 수신하면 난수를 자식에게 송신하고, 자식은 부모에게 응답을 되돌려 준다.
   */
  parent ! Send
}
