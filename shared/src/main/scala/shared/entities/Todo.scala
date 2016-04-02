package shared.entities

import scala.scalajs.js.annotation.JSExportAll

@JSExportAll
case class Todo(id: Int, body: String, isDone: Boolean)
