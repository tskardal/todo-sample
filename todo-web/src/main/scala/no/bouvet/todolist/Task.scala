package no.bouvet.todolist

import org.squeryl.{Session, SessionFactory, Schema}
import org.squeryl.adapters.DerbyAdapter
import java.sql._

class Task(val id: Int, val title: String, val complete: Boolean) {
  def this() = this(0, "", false)
}

object TaskDB extends Schema {
  val tasks = table[Task]
  create
  printDdl

  tasks.insert(new Task(1, "First task", false))
  tasks.insert(new Task(2, "Second task", false))
  tasks.insert(new Task(3, "Third task", false))
}