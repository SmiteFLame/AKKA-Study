package com.scala.akka.become

import akka.actor.Actor

class BecomeUnBecomeActor extends Actor {
  def receive: Receive = {
    /**
     *  현재 Actor의 상태를 알려주는 값이다.
     */
    case true => context.become(isStateTrue)
    case false => context.become(isStateFalse)
    case _ => println("don't know what you want to say !! ")
  }


  def isStateTrue: Receive  = {
    case msg : String => println(s"$msg")
    case false => context.become(isStateFalse)
  }

  def isStateFalse: Receive  = {
    case msg : Int => println(s"$msg")
    case true =>  context.become(isStateTrue)
  }
}