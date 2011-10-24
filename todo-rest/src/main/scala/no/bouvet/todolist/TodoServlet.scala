package no.bouvet.todolist

import org.scalatra._
import net.liftweb.json.Serialization.write
import net.liftweb.json.{NoTypeHints, Serialization}
import org.squeryl._
import PrimitiveTypeMode._
import scalate.ScalateSupport

class TodoServlet extends ScalatraServlet with ScalateSupport with UrlSupport {

  get("/") {
    contentType = "text/html"
    scaml("index")
  }

  get("/tasks") {
    var tasks = List[Task]()
    transaction {
      tasks = from(TaskDB.tasks)(s => select(s)).toList
    }
    implicit val formats = Serialization.formats(NoTypeHints)
    write(tasks)
  }

  get("/add") {
    contentType = "text/html"
    scaml("task-form")
  }

  post("/add") {
    transaction {
      val t = new Task(0, params("title"), false)
      TaskDB.tasks.insert(t)
    }
    status(201)
  }

  put("/mark-complete/:id") {
    SessionFactory.newSession.bindToCurrentThread
    val taskId = params("id").toLong
    var task = TaskDB.tasks.where(s => s.id === taskId).single
    implicit val formats = Serialization.formats(NoTypeHints)
    transaction {
      task.complete = true
      TaskDB.tasks.update(task)
    }
    write(task)
  }

  notFound {
    contentType = "text/html"
    status(404)
    <h1>Not found</h1>
  }

  protected def contextPath = request.getContextPath
}
