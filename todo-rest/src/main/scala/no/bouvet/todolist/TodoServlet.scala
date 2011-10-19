package no.bouvet.todolist

import org.scalatra._
import org.squeryl._
import adapters.DerbyAdapter
import PrimitiveTypeMode._
import scalate.ScalateSupport
import java.sql.DriverManager

class TodoServlet extends ScalatraServlet with ScalateSupport with UrlSupport {

  Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
  SessionFactory.concreteFactory = Some(()=>
    Session.create(
      DriverManager.getConnection("jdbc:derby:todolist;create=true"),
      new DerbyAdapter
    )
  )

  get("/") {
    // list tasks
    SessionFactory.newSession.bindToCurrentThread
    val tasks = from(TaskDB.tasks)(s => select(s)).toList
    contentType = "text/html"
    scaml("index", "tasks" -> tasks)
  }

  get("/add") {
    contentType = "text/html"
    scaml("task-form")
  }

  post("/add") {
    // add the task
    SessionFactory.newSession.bindToCurrentThread
    transaction {
      val t = new Task(0, params("title"), false)
      TaskDB.tasks.insert(t)
    }
    redirect(url("/"))
  }

  get("/mark-complete/:id") {
    // mark as complete
    SessionFactory.newSession.bindToCurrentThread
    val taskId = params("id").toLong
    transaction {
      update(TaskDB.tasks) (t =>
        where(t.id === taskId)
        set(t.complete := true)
      )
    }
    redirect(url("/"))
  }

  notFound {
    <h1>Not found</h1>
  }

  protected def contextPath = request.getContextPath
}
