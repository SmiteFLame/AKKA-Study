package com.scala.http.goticket

import scala.concurrent.Future

import akka.actor._
import akka.util.Timeout

object BoxOffice {
  def props(implicit timeout: Timeout) = Props(new BoxOffice)
  def name = "boxOffice"

  case class CreateEvent(name: String, tickets: Int) /* 이벤트를 만드는 메시지 */
  case class GetEvent(name: String)                  /* 이벤트를 얻는 메시지 */
  case object GetEvents                              /* 모든 이벤트를 요청하는 메시지 */
  case class GetTickets(event: String, tickets: Int) /* 이벤트의 타켓을 취소하는 메시지 */
  case class CancelEvent(name: String)               /* 이벤트를 취소하는 메시지 */

  case class Event(name: String, tickets: Int)       /* 이벤트를 설명하는 메시지 */
  case class Events(events: Vector[Event])           /* 이벤트 목록을 설명하는 메시지 */

  sealed trait EventResponse                         /* CreateEvent에 응답하는 메시지 */
  case class EventCreated(event: Event) extends EventResponse /* 이벤트가 생성되었을 음을 알려주는 메시지 */
  case object EventExists extends EventResponse      /* 이벤트가 이미 있음을 알려주는 메시지 */

}

class BoxOffice(implicit timeout: Timeout) extends Actor {
  import BoxOffice._
  import context._

  /**
   * 액터 시스템의 컨택서느가 아닌 자신의 컨텍스트를 사용한다
   * 자신의 컨텍스트를 사용하여 만든 액터는 그 액터의 자식이 되며 부모 액터의 관리를 받는다.
   */

  def createTicketSeller(name: String) =
    context.actorOf(TicketSeller.props(name), name)  /* 자신의 컨텍스트를 사용해 TicketSeller를 만든다. 테스트시 오버라이드 하기 쉽게 정의 */

  def receive = {
    case CreateEvent(name, tickets) =>
      def create() = { /* Tickek Seller를 만드는 로컬 메서드다. 티켓을 티켓 판매원에게 추가하고 EventCreated에 응답한다. */
        val eventTickets = createTicketSeller(name)
        val newTickets = (1 to tickets).map { ticketId =>
          TicketSeller.Ticket(ticketId)
        }.toVector
        eventTickets ! TicketSeller.Add(newTickets)
        sender() ! EventCreated(Event(name, tickets))
      }
      context.child(name).fold(create())(_ => sender() ! EventExists) /* 이벤트를 만들고 Event Created로 응답하거나 Event Exisit로 응답한다. */

    case GetTickets(event, tickets) =>
      def notFound() = sender() ! TicketSeller.Tickets(event)
      def buy(child: ActorRef) =
        child.forward(TicketSeller.Buy(tickets))

      context.child(event).fold(notFound())(buy)

    case GetEvent(event) =>
      def notFound() = sender() ! None
      def getEvent(child: ActorRef) = child forward TicketSeller.GetEvent
      
	  context.child(event).fold(notFound())(getEvent)

    case GetEvents =>
      import akka.pattern.ask
      import akka.pattern.pipe

      def getEvents = context.children.map { child =>
        self.ask(GetEvent(child.path.name)).mapTo[Option[Event]]
      }
      def convertToEvents(f: Future[Iterable[Option[Event]]]) =
        f.map(_.flatten).map(l=> Events(l.toVector))

      pipe(convertToEvents(Future.sequence(getEvents))) to sender()


    case CancelEvent(event) =>
      def notFound() = sender() ! None
      def cancelEvent(child: ActorRef) = child forward TicketSeller.Cancel
      context.child(event).fold(notFound())(cancelEvent)
  }
}

