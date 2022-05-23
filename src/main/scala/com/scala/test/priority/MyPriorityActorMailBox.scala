package com.scala.test.priority

import akka.actor.ActorSystem
import akka.dispatch.{PriorityGenerator, UnboundedPriorityMailbox}
import com.typesafe.config.Config

class MyPriorityActorMailBox(settings: ActorSystem.Settings, config: Config) extends UnboundedPriorityMailbox(
  // PriorityGenerator
  PriorityGenerator {
    case x: Int => 1
    case x: String => 0
    case x: Long => 2
    case _ => 3
  })
