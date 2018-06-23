package com.techmonad.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import akka.util.ByteString



import scala.concurrent.Future

object ClientApp extends App {

  implicit val system: ActorSystem = ActorSystem()

  import system.dispatcher

  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val request: HttpRequest = HttpRequest(uri = "https://www.google.com/")

  val response: Future[HttpResponse] = Http().singleRequest(request)

  response.map { httpResponse =>
    val bytes: Source[ByteString, Any] = httpResponse.entity.dataBytes
    bytes.runFold(ByteString(""))(_ ++ _)
      .foreach { body => println(body.utf8String) }
  }

}
