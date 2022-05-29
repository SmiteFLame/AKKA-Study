package com.scala.akka.control

import akka.actor.Actor
import akka.dispatch.ControlMessage

class Logger extends Actor {

  def receive = {
    case MyControlMessage => println("Oh, I have to process Control message first")
    case x => println(x.toString)

  }
}