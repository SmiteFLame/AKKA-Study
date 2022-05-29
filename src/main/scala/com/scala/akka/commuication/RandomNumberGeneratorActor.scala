package com.scala.akka.commuication

import akka.actor.Actor

import scala.util.Random

class RandomNumberGeneratorActor extends Actor {

  import Message._

  override def receive: Receive = {
    case GiveRandomNumber =>
      println("received a message to generate a random integer")
      val randomNumber = Random.nextInt()
      sender ! Done(randomNumber)
  }
}
