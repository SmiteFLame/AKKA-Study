package com.scala.http.goticket

import scala.concurrent.Future
import scala.util.{Failure, Success}

import akka.actor.{ ActorSystem , Actor, Props }
import akka.event.Logging
import akka.util.Timeout

import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import com.typesafe.config.{ Config, ConfigFactory }

/**
 * 전체 구조
 * 1. ActorSystem을 만든다.
 * 2. RestApi 인터페이스가 앱의 최 상위 액터이다.
 * 3. BoxOffice 인터페이스는 BoxOffice 액터를 하나 만든다.
 * 4.
 */

object Main extends App
    with RequestTimeout {

  /**
   * 설정에서 호스트와 포트 정보를 가지고 온다.
   */
  val config = ConfigFactory.load() 
  val host = config.getString("http.host")
  val port = config.getInt("http.port")

  /**
   * bindAndHandle는 비동기 적이다
   * ExecutionContext를 암시적으로 사용을 해야 한다.
   */
  implicit val system = ActorSystem() 
  implicit val ec = system.dispatcher

  /**
   * RestApi는 Http 루트를 제공한다.
   */
  val api = new RestApi(system, requestTimeout(config)).routes
 
  implicit val materializer = ActorMaterializer()
  val bindingFuture: Future[ServerBinding] = {
    /**
     * RestApi 루트를 가지고 Http 서버를 시작한다.
     */
    Http().bindAndHandle(api, host, port)
  }

  /**
   * ActorSystem 만들어지자마자 활성화되며 필요한 스레드 풀을 즉시 시작한다.
   * http()는 HTTP 확장을 반환하고 bindAndHandle은 RestApi에 정의된 루트를 HTTP 서버와 연결한다.
   * ActorSystem 비 데몬 스레드 (메인 스레드가 언제든지 중단시킬 수 있는) 를 만들고 계속 실행되므로 Main 앱도 즉시 중단되지 않고 계속 실행된다.
   */

  val log =  Logging(system.eventStream, "go-ticks")
  bindingFuture.map { serverBinding =>
    log.info(s"RestApi bound to ${serverBinding.localAddress} ")
  }.onComplete { 
    case Success(v) =>
	case Failure(ex) =>
      log.error(ex, "Failed to bind to {}:{}!", host, port)
      system.terminate()
  }
}

/**
 * akka-http 서버 설정에 있는 기본 요청 타임아웃을 사용한다.
 */
trait RequestTimeout {
  import scala.concurrent.duration._
  def requestTimeout(config: Config): Timeout = {
    val t = config.getString("akka.http.server.request-timeout")
    val d = Duration(t)
    FiniteDuration(d.length, d.unit)
  }
}


