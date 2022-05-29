package com.scala.code.test

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global

object Advanced extends App {
  // lazy evaluation
  lazy val aLazyValue = 2
  // 불러오기 전까지 실행을 하지 않는다.
  lazy val lazyValueWithSideEffect = {
    println("I am so very lazy!")
    43
  }

  val eagerValue = lazyValueWithSideEffect + 1
  // useful in infinite collections

  // "pesudo-colelctions": Option, Try
  def methodWhichCanReturnNull(): String = "hello, Scala"

  if (methodWhichCanReturnNull() == null) {
    // defensive null pointer
  }

  val anOption = Option(methodWhichCanReturnNull())
  // option = "collection" which contains at most one element: Some(Value) or None(equals Null)

  val stringProcessing = anOption match {
    case Some(string) => s"I have obtained a valid string : $string"
    case None => "I obstaned nothing"
  }

  // map, flatMap, filter
  def methodWhichCanThrowException(): String = throw new RuntimeException

  try {
    methodWhichCanThrowException()
  } catch {
    case e: Exception => "defend aginst this evil exception"
  }

  // 아래로 줄일 수 있다.
  val aTry = Try(methodWhichCanThrowException())
  // a Try = "Coeection" with either a value if the code went well, or an exception if the code threw one

  val anotherStringProcessing = aTry match {
    case Success(validValue) => s"I have obtained a valid String: $validValue"
    case Failure(exception) => s"I have obtained an exception: $exception"
  }

  /**
   * Evalutate Something on another thread
   * (asynchronous programming)
   */

  val aFuture = Future({ // Future.apply
    println("Loading...")
    Thread.sleep(1000)
    println("I have compute a value.")
    67
  })

  val aFuture2 = Future({ // Future.apply
    println("Loading2...")
    Thread.sleep(1000)
    println("I have compute a value2.")
    67
  })

  /**
   * 동시성을 위해서 따로따로 실행이 된다.
   */

  Thread.sleep(2000)

  // future is a "collection" which contains a value when its evaluted
  // futer is composable with map, flatMap and filter

  // mondas

  /**
   * Implicits basics
   */
  // #1: implicit arguments
  def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1

  implicit val myImplicitInt = 46
  println(aMethodWithImplicitArgs) // aMethodWithImplicitArgs(myImplicitInt)

  // #2: implicit conversions
  implicit class MyRichInteger(n: Int) {
    def isEven() = n % 2 == 0
  }

  println(23.isEven()) // new MyRichInteger(23).isEven()
}
