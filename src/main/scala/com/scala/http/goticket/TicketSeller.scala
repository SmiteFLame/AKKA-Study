package com.scala.http.goticket

import akka.actor.{ Actor, Props, PoisonPill }
object TicketSeller {
  def props(event: String) = Props(new TicketSeller(event))

  case class Add(tickets: Vector[Ticket])                           /* 티켓을 추가하는 메시지 */
  case class Buy(tickets: Int)                                      /* 티켓을 구매하는 메시지 */
  case class Ticket(id: Int)                                        /* 이벤트를 설명하는 메시지 */
  case class Tickets(event: String,                                 /* 이벤트 목록을 설명하는 메시지 */
                     entries: Vector[Ticket] = Vector.empty[Ticket])
  case object GetEvent                                              /* 이벤트가 생성되었음을 알려주는 메시지 */
  case object Cancel                                                /* 이벤트가 이미 있음을 알려주는 메시지 */

}


class TicketSeller(event: String) extends Actor {
  import TicketSeller._

  var tickets = Vector.empty[Ticket]  /* 티켓 목록 */

  def receive = {
    case Add(newTickets) => tickets = tickets ++ newTickets  /* Ticket 메시지를 받으면 기존 티켓 목록에 새로운 티켓을 추가한다. */
    case Buy(nrOfTickets) => /* Ticket이 충분이 남아 있다면 요청받은 수만큼 타켓을 빼내서 그 타켓들을 Ticket 메시지에 담아 응답한다 그렇지 않은 경우 빈 Ticket 메시지를 보낸다. */
      val entries = tickets.take(nrOfTickets)
      if(entries.size >= nrOfTickets) {
        sender() ! Tickets(event, entries)
        tickets = tickets.drop(nrOfTickets)
      } else sender() ! Tickets(event)
    case GetEvent => sender() ! Some(BoxOffice.Event(event, tickets.size)) /* GetEvent를 받으면 남은 티켓 수를 포함하는 이벤트를 반환한다. */
    case Cancel =>
      sender() ! Some(BoxOffice.Event(event, tickets.size))
      self ! PoisonPill
  }
}

