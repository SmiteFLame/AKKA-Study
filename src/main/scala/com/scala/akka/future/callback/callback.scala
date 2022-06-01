package com.scala.akka.future.callback

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object callback extends App {
  val future = Future(1 + 2).mapTo[Int]
  future onComplete{
    case Success(result) => println(s"result is $result")
    case Failure(fail) => fail.printStackTrace()
  }
  println("Executed before callback")
  /**
   * 출력은 Executed 다음 Success가 된다.
   */
}
