package com.scala.http.spider.controller

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import scala.concurrent.ExecutionContext

class HttpRoute()(implicit executionContext: ExecutionContext) {

  private val helloRoute = new HelloRoute()

  val route: Route =
    pathPrefix("hello") {
      helloRoute.route
    }
}