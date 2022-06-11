package com.scala.http.spider.controller

import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContext

class HelloRoute()(implicit executionContext: ExecutionContext) {

  val route = pathPrefix("") {
    get {
      complete("Hello Spider")
    }
  }
}