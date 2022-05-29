package com.scala.akka.parent

import akka.actor.{ActorSystem, Props}

object ParentChild extends App{
  val actorSystem = ActorSystem("Supervision")
  val parent = actorSystem.actorOf(Props[ParentActor],"parent")
  parent ! CreateChild
}
