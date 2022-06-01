package com.scala.akka.future.parallel

object Fib {
  def fib(n: Int): Int = {
    def fib_tail(n: Int, a: Int, b: Int): Int = n match {
      case 0 => a
      case _ => fib_tail(n - 1, b, a + b)
    }
    fib_tail(n, 0, 1)
  }

}
