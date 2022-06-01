package com.scala.akka.future.insider

import akka.actor.Actor

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

class FutureActor extends Actor {

  import context.dispatcher

  override def receive: Receive = {
    case (a: Int, b: Int) => val f = Future(a + b)
      (Await.result(f, 10 seconds))

  }
}
