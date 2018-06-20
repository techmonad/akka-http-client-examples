package com.techmonad.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest}
import akka.stream.Materializer
import akka.util.ByteString

import scala.concurrent.Future

trait HttpClient {

  def get(url: String)(implicit m: Materializer, system: ActorSystem): Future[String] =
    Http().singleRequest(HttpRequest(uri = url))
      .flatMap { httpResponse =>
        httpResponse.entity
          .dataBytes
          .runFold(ByteString(""))(_ ++ _)
          .map { str => str.utf8String }
      }


  def post(url: String, body: String)(implicit m: Materializer, system: ActorSystem): Future[String] =
    Http().singleRequest(HttpRequest(uri = url, method = HttpMethods.POST, entity = body))
      .flatMap { httpResponse =>
        httpResponse.entity
          .dataBytes
          .runFold(ByteString(""))(_ ++ _)
          .map { str => str.utf8String }
      }

}
