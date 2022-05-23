package com.scala.test.message
import akka.actor.{Props, Actor, ActorSystem, ActorRef}
import akka.dispatch.{MailboxType, ProducesMessageQueue, Envelope, MessageQueue}
import com.typesafe.config.Config

object CustomMailBox extends App{
  val actorSystem = ActorSystem("helloAkka")
  val actor = actorSystem.actorOf(Props[MySpecialActor].withDispatcher("custom-dispatcher"))
  val actor1 = actorSystem.actorOf(Props[MyActor], "xyz")
  val actor2 = actorSystem.actorOf(Props[MyActor], "MyActor")
  actor1 ! ("hello", actor)
  actor2 ! ("hello", actor)
}
