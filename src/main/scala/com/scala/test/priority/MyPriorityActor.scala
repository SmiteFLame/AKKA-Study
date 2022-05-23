package com.scala.test.priority

import akka.actor.Actor

class MyPriorityActor extends Actor {
  override def receive: PartialFunction[Any, Unit] = {
    case x: Int => println(x)
    case x: String => println(x)
    case x: Long => println(x)
    case x => println(x)
  }

}
