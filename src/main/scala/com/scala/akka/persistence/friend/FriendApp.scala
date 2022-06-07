package com.scala.akka.persistence.friend

import akka.actor.ActorSystem
import akka.persistence.{Recovery, SnapshotSelectionCriteria}

/**
 * 지속성 액터는 persistence ID 기반으로 상태를 복구한다.
 * 주어진 지속성 액터를 위해, 그리고 데이터 저장소 내에 있어서 유일해야 한다.
 *
 * 이벤트를 지속시키고 복구할 때 저널의 일관성을 보장하는데 필요하다.
 * 아카 지속성은 이벤트와 스냡샷의 각 저널과 스냡샷 저장소를 확인한다.
 *
 * 가장 최근의 스냅샷을 적용하고 그 후 잃어버린 이벤트를 재연하는 것이다.
 */

object FriendApp extends App {
  val system = ActorSystem("test")
  /**
   * 액터를 제공할 때 Recovery 객체를 제공해야 한다.
   * 이 객체의 복구 방법을 맞춤화 할 수 있다.
   * 4명의 친구를 추가하고 snap샷을 얻는다.
   */
  val hector = system.actorOf(FriendActor.props("Hector", Recovery()))
  hector ! AddFriend(Friend("Laura"))
  hector ! AddFriend(Friend("Nancy"))
  hector ! AddFriend(Friend("Oliver"))
  hector ! AddFriend(Friend("Steve"))
  hector ! "snap"
  hector ! RemoveFriend(Friend("Oliver"))
  hector ! "print"
  Thread.sleep(2000)
  system.terminate()
}

/**
 * 기본적인 복구가 어떻게 나타나는지 보게 된다.
 * 출력을 보면, 먼저 스냡샷을 받고 Oliver 를 삭제하는 이벤트를 재연한다.
 * 아카 지속성은 가장 마지막 스냅샷을 얻어내고 그 시점 이후의 이벤트를 재연한다.
 */
object FriendRecoveryDefault extends App {
  val system = ActorSystem("test")
  val hector = system.actorOf(FriendActor.props("Hector", Recovery()))
  hector ! "print"
  Thread.sleep(2000)
  system.terminate()
}

/**
 * 복구 객체에 fromSnapShot = SnapshotSelectionCriteria.None 을 주여졌다.
 * 지속성 모듈에 어떤 스냅샷도 사용하지 말라고 말하는 것이며, 로그를 보면 모든 이벤트가 재연되었음을 알 수 있다.
 */
object FriendRecoveryOnlyEvents extends App {
  val system = ActorSystem("test")
  val recovery = Recovery(fromSnapshot = SnapshotSelectionCriteria.None)
  val hector = system.actorOf(FriendActor.props("Hector", recovery))
  hector ! "print"
  Thread.sleep(2000)
  system.terminate()
}

/**
 * toSequenceNr 값이 2로 주어졌다.
 * 이는 최대의 이벤트가 재연될것을 뜻한다.
 */
object FriendRecoveryEventsSequence extends App {
  val system = ActorSystem("test")
  val recovery = Recovery(fromSnapshot = SnapshotSelectionCriteria.None, toSequenceNr = 2)
  val hector = system.actorOf(FriendActor.props("Hector", recovery))
  Thread.sleep(2000)
  system.terminate()
}

/**
 * replayMax 값이 3번으로 주어졌다.
 * 로그에서 보는 바와 같이 오직 3개의 이벤트만 재연이 된다.
 * 저널에서 지속된 모든 이벤트 대신 필요한 이벤트만 재연하도록 toSequenceNr과 replayMax 값으로 재생하는 것이 가능하다
 */
object FriendRecoveryEventsReplay extends App {
  val system = ActorSystem("test")
  val recovery = Recovery(fromSnapshot = SnapshotSelectionCriteria.None, replayMax = 3)
  val hector = system.actorOf(FriendActor.props("Hector", recovery))
  Thread.sleep(2000)
  system.terminate()
}
