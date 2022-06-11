ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

libraryDependencies ++= {
  val akkaVersion = "2.6.19"
  val akkaHttpVersion = "10.2.9"
  Seq(
    "com.typesafe.akka" %% "akka-actor"      % akkaVersion,
    "com.typesafe.akka" %% "akka-stream"      % akkaVersion,
    "com.typesafe.akka" %% "akka-persistence"      % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core"  % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http"       % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json"  % akkaHttpVersion,
//    "com.typesafe.akka" %% "akka-agent"  % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j"      % akkaVersion,
    "ch.qos.logback"    %  "logback-classic" % "1.2.3",
    "com.typesafe.akka" %% "akka-testkit"    % akkaVersion   % "test",
    "org.iq80.leveldb"  % "leveldb" % "0.7",
//    "org.scalatest"     %% "scalatest"       % "3.0.1"       % "test"
  )
}

lazy val root = (project in file("."))
  .settings(
    name := "hello-akka"
  )
