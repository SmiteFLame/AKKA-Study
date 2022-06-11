package com.scala.akka.future.parallel

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Parallel extends App {
  import Fib._
  val t1 = System.currentTimeMillis()
  val sum = fib(100) + fib(100) + fib(100)
  println(s"sum is $sum time taken in sequential computation ${System.currentTimeMillis - t1 / 1000.0}")
  val t2 = System.currentTimeMillis()
  val future1 = Future(fib(100))
  val future2 = Future(fib(100))
  val future3 = Future(fib(100))

  val future = for {
    x <- future1
    y <- future2
    z <- future3
  } yield (x + y + z)

  future. onComplete{
    case sum =>
      val endTime = ((System.currentTimeMillis() - t2) / 1000.0)
      println(s"sum is $sum time taken in parallel computation $endTime seconds")
  }
  Thread.sleep(5000)
}
