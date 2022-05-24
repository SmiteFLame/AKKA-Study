package com.scala.test.send

import akka.actor.{Actor, ActorRef, Props}

import scala.util.Random

class ParentActor extends Actor {
  val random = new Random()
  var childs = scala.collection.mutable.ListBuffer[ActorRef]()

  override def receive: Receive = {
    case CreateChild => childs ++= List(context.actorOf(Props[DoubleActor]))
    case Send =>
      println(s"Sending messages to child")
      childs.zipWithIndex map {
        case (child, value) =>
          child ! DoubleValue(random.nextInt(10))
      }
    case Response(x) => println(s"Parent: Response from child ${sender.path.name} is $x")
  }
}
