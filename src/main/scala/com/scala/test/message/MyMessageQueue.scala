package com.scala.test.message

import akka.actor.ActorRef
import akka.dispatch.{Envelope, MessageQueue}

import java.util.concurrent.ConcurrentLinkedQueue

/**
 * 사용자의 메일 박스를 정의
 */
class MyMessageQueue extends MessageQueue {
  private final val queue = new ConcurrentLinkedQueue[Envelope]()

  override def enqueue(receiver: ActorRef, handle: Envelope): Unit = {
    if (handle.sender.path.name == "MyActor") {
      handle.sender ! "Hey dude, How are you?, I Know your name, processing your request"
      queue.offer(handle)
    }
    else {
      handle.sender ! "I dont talk to stranger, I cant process your request"
    }
  }

  override def dequeue(): Envelope = queue.poll()

  override def numberOfMessages: Int = queue.size

  override def hasMessages: Boolean = !queue.isEmpty

  override def cleanUp(owner: ActorRef, deadLetters: MessageQueue): Unit = {
    while (hasMessages) {
      deadLetters.enqueue(owner, dequeue())
    }
  }
}