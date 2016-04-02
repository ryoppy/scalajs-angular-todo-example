package app.service

import biz.enef.angulate.Service
import biz.enef.angulate.core.{HttpPromise, HttpService}
import io.circe._
import io.circe.generic.auto._
import io.circe.scalajs._
import shared.entities.{ListResponse, Todo}

import scala.scalajs.js

class TodoService($http: HttpService) extends Service {

  def list(): HttpPromise[ListResponse[Todo]] = {
    $http.get("/todo").map(decode[ListResponse[Todo]])
  }

  def add(body: String): HttpPromise[Todo] = {
    $http.post("/todo", js.Dictionary("body" -> body)).map(decode[Todo])
  }

  def toggle(id: Int): HttpPromise[Unit] = {
    $http.put[js.Any](s"/todo/toggle/$id").map(_ => ())
  }

  def delete(id: Int): HttpPromise[Unit] = {
    $http.delete[js.Any](s"/todo/$id").map(_ => ())
  }

  private def decode[A: Decoder](j: js.Any): A =
    decodeJs[A](j).fold(e => throw e, x => x)
}
