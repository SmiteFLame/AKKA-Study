package com.scala.http.first

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

object AkkaHttp5mins {
  implicit val system = ActorSystem() // Akka Actors
  implicit val materializer = ActorMaterializer() // Akka Streams

  import system.dispatcher // "thread pool"

  val source =
    """
      |object Simple App
      | val aField = 2
      | def aMethod(x: Int) = x +  1
      | def main(args: Array[String]): Unit = println(aField)
      |}
    """.stripMargin


  val request = HttpRequest(
    method = HttpMethods.POST,
    uri = "http://markup.su/api/highlighter",
    entity = HttpEntity(
      ContentTypes.`application/json`,
      "{}"
    )
  )

  def sendRequest() = {
    val responseFuture: Future[HttpResponse] = Http().singleRequest(request)
    val entityFuture: Future[HttpEntity.Strict] = responseFuture.flatMap(response => response.entity.toStrict(2 seconds))
    entityFuture.map(entity => entity.data.utf8String)
  }

  def main(args: Array[String]): Unit = {
    sendRequest().foreach(println)
  }
}
