package services

import javax.inject._

import shared.entities.Todo

trait TodoService {
  def findAll(): Seq[Todo]
  def add(body: String): Todo
  def toggle(id: Int): Unit
  def delete(id: Int): Unit
}

@Singleton
class TodoServiceImpl extends TodoService {
  val values = scala.collection.mutable.ListBuffer[Todo]()

  def findAll(): Seq[Todo] = values

  def add(body: String): Todo = (values += Todo(latestId + 1, body, false)).last

  def toggle(id: Int): Unit = {
    val i = values.indexWhere(_.id == id)
    val t = values(i)
    values.update(i, t.copy(isDone = !t.isDone))
  }

  def delete(id: Int): Unit = values.find(_.id == id).foreach(values -= _)

  private def latestId = if (values.isEmpty) 0 else values.maxBy(_.id).id
}
