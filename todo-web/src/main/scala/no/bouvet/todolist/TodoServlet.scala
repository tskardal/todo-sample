package no.bouvet.todolist

import org.scalatra._
import java.net.URL
import scalate.ScalateSupport

class TodoServlet extends ScalatraServlet with ScalateSupport {

  get("/") {
    // list tasks
    contentType = "text/html"
    scaml("index", "tasks" -> createTasks, "title" -> "lalals")
  }

  def createTasks() : List[Task] = {
    val tasks = List(new Task(1, "Test", false), new Task(2, "Other test", true))
    tasks
  }

  get("/add") {
    // form for adding task
  }

  post("/add") {
    // add the task
  }

  post("/mark-complete/:id") {
    // mark as complete
  }

  notFound {
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound() 
  }
}
