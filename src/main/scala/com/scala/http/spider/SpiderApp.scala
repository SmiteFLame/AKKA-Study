package com.scala.http.spider

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.complete
import com.scala.http.spider.controller.HttpRoute
import com.typesafe.config.ConfigFactory

object SpiderApp extends App {
  val config = ConfigFactory.load()
//  val host = config.getString("http.host")
//  val port = config.getInt("http.port")

  implicit val system = ActorSystem()
  implicit val ec = system.dispatcher

  Http().newServerAt("localhost", 10080).bind(new HttpRoute().route)
}
