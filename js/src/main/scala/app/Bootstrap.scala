package app

import app.ctrl.TodoCtrl
import app.service.TodoService
import biz.enef.angulate._
import biz.enef.angulate.ext.{Route, RouteProvider}

import scala.scalajs.js.JSApp

object Bootstrap extends JSApp {
  def main(): Unit = {
    val module = angular.createModule("mvc", Seq("ngRoute"))
    module.controllerOf[TodoCtrl]
    module.serviceOf[TodoService]

    module.config(($routeProvider: RouteProvider) => {
      $routeProvider.
        when("/", Route(templateUrl = "/assets/tpl/todo/list.html")).
        when("/todo/add", Route(templateUrl = "/assets/tpl/todo/add.html")).
        otherwise(Route(redirectTo = "/"))
    })
  }
}
