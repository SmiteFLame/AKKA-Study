package com.scala.akka.future.reduce

import akka.util.Timeout

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.DurationInt
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps

object ReducingFutures extends App{
  val timeout = Timeout(10 seconds)
  val listOfFutures = (1 to 10).map(Future(_))
  val finalFuture = Future.reduce(listOfFutures)(_ + _)
  println(s"sum of numbers from 1 to 10 us ${Await.result(finalFuture, 10 seconds)}")

  /**
   * 리스트에서 주로 하는 것 처럼 퓨처의 시퀀스와 이들을 축소 시키는 함수를 받는 Future reduce 메서드로 퓨처를 축소했다.
   */
}
