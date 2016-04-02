package controllers

import javax.inject._

import shared.entities.ListResponse
import play.api.mvc._
import services.TodoService
import io.circe._, io.circe.generic.auto._, io.circe.syntax._
import play.api.data._
import play.api.data.Forms._

@Singleton
class TodoController @Inject()(todoService: TodoService) extends Controller {

  def list = Action {
    Ok(ListResponse(todoService.findAll()).asJson.toString)
  }

  def add = Action { implicit req =>
    Form("body" -> text).bindFromRequest.fold(
      _ => NotFound,
      body => Ok(todoService.add(body).asJson.toString)
    )
  }

  def toggle(id: Int) = Action {
    todoService.toggle(id)
    Ok
  }

  def delete(id: Int) = Action {
    todoService.delete(id)
    Ok
  }
}
