ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.11.7"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.4.4"

libraryDependencies ++= {
  val akkaVersion = "2.5.26"
  val akkaHttpVersion = "10.1.11"
  Seq(
    "com.typesafe.akka" %% "akka-actor"      % akkaVersion,
    "com.typesafe.akka" %% "akka-stream"      % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core"  % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http"       % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json"  % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-agent"  % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j"      % akkaVersion,
    "ch.qos.logback"    %  "logback-classic" % "1.2.3",
    "com.typesafe.akka" %% "akka-testkit"    % akkaVersion   % "test",
    "org.scalatest"     %% "scalatest"       % "3.0.1"       % "test"
  )
}

lazy val root = (project in file("."))
  .settings(
    name := "hello-akka"
  )
