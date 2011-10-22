package no.bouvet.todolist

import org.scalatra.test.scalatest.ScalatraFunSuite

class TodoServletTest extends ScalatraFunSuite {
  addServlet(classOf[TodoServlet], "/*")

  test("simple get") {
    get("/") {
      status should equal(200)
      body should include("Loading tasks")
    }
  }
}