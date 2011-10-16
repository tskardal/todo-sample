package no.bouvet.todolist

import org.squeryl.adapters.DerbyAdapter
import java.sql._
import org.squeryl.{KeyedEntity, Session, SessionFactory, Schema}

class Task(val id: Long, var title: String, var complete: Boolean) extends KeyedEntity[Long]
{
  def this() = this(0, "", false)
}

object TaskDB extends Schema {
  val tasks = table[Task]
  drop
  create
  printDdl

  tasks.insert(new Task(1, "First task", false))
  tasks.insert(new Task(2, "Second task", false))
  tasks.insert(new Task(3, "Third task", false))
}