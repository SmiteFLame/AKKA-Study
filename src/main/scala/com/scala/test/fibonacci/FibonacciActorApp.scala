package com.scala.test.fibonacci

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object FibonacciActorApp extends App {
  implicit val timeout = Timeout(10 seconds)
  val actorSystem = ActorSystem("HelloAkka")
  val actor = actorSystem.actorOf(Props[FibonacciActor])
  val future = (actor ? 15).mapTo[Int]
  val fibonacciNumber = Await.result(future, 10 seconds)
  println(fibonacciNumber)
}