package shared.entities

import scala.scalajs.js.annotation.JSExportAll

@JSExportAll
case class ListResponse[A](items: Seq[A])
