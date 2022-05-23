ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.11.7"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.4.4"

lazy val root = (project in file("."))
  .settings(
    name := "hello-akka"
  )
