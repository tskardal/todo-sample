organization := "no.bouvet"

name := "todo-rest"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.1"

seq(webSettings :_*)

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.0.1",
  "org.scalatra" %% "scalatra-scalate" % "2.0.1",
  "org.squeryl" %% "squeryl" % "0.9.4",
  "org.apache.derby" % "derby" % "10.7.1.1",
  "net.liftweb" % "lift-json_2.9.1" % "2.4-M4",
  "org.eclipse.jetty" % "jetty-webapp" % "7.4.5.v20110725" % "jetty",
  "javax.servlet" % "servlet-api" % "2.5" % "provided"
)

resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
