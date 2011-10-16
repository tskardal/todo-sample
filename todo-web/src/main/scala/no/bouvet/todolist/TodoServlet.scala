package no.bouvet.todolist

import org.scalatra._
import org.squeryl._
import adapters.DerbyAdapter
import PrimitiveTypeMode._
import scalate.ScalateSupport
import java.sql.DriverManager

class TodoServlet extends ScalatraServlet with ScalateSupport {

  Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
  SessionFactory.concreteFactory = Some(()=>
    Session.create(
      DriverManager.getConnection("jdbc:derby:todolist;create=true"),
      new DerbyAdapter
    )
  )

  get("/") {
    // list tasks
    contentType = "text/html"
    scaml("index", "tasks" -> createTasks, "title" -> "lalals")
  }

  def createTasks() : List[Task] = {
    SessionFactory.newSession.bindToCurrentThread

    var tasks: List[Task] = List[Task]()
    transaction {
      tasks = from(TaskDB.tasks)(s => select(s)).toList
    }
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
    <h1>Not found</h1>
  }
}
