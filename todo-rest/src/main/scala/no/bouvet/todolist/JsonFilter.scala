package no.bouvet.todolist

import org.scalatra.ScalatraFilter
import org.squeryl.{Session, SessionFactory}
import java.sql.DriverManager
import org.squeryl.adapters.DerbyAdapter

class JsonFilter extends ScalatraFilter {
  Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
  SessionFactory.concreteFactory = Some(() =>
    Session.create(
      DriverManager.getConnection("jdbc:derby:todolist;create=true"),
      new DerbyAdapter
    )
  )

  before() {
    SessionFactory.newSession.bindToCurrentThread
    contentType = "application/json"
  }
}