package app.ctrl

import app.service.TodoService
import biz.enef.angulate.Controller
import biz.enef.angulate.core.Location
import org.scalajs.dom.raw.Event
import shared.entities.Todo

import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce

class TodoCtrl($location: Location, todoService: TodoService) extends Controller {
  var todos = js.Array[Todo]()
  var body = ""

  def list() = {
    todoService.list().onSuccess { case j =>
      todos = j.items.toJSArray
    }
  }

  def add(e: Event) = {
    e.preventDefault()
    todoService.add(body).onSuccess { case j =>
      $location.path("/#/")
    }
  }

  def toggle(id: Int) = {
    todoService.toggle(id).onSuccess { case _ =>
      $location.path("/#/")
    }
  }

  def delete(id: Int) = {
    todoService.delete(id).onSuccess { case _ =>
      $location.path("/#/")
    }
  }
}
