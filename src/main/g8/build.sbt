Global / onChangedBuildSource := ReloadOnSourceChanges

// give the user a nice default project!
ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file(".")).settings(
  name := "my-flink-scala-proj",
  libraryDependencies ++= Seq(
    "org.flinkextended" %% "flink-scala-api" % "$flinkVersion$_1.1.4",
    "org.apache.flink" % "flink-clients" % "$flinkVersion$" % Provided
  )
)

// make run command include the provided dependencies
Compile / run := Defaults
  .runTask(
    Compile / fullClasspath,
    Compile / run / mainClass,
    Compile / run / runner
  )
  .evaluated

// stays inside the sbt console when we press "ctrl-c" while a Flink programme executes with "run" or "runMain"
Compile / run / fork := true
Global / cancelable := true
