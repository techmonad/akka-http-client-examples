name := "akka-http-client-examples"

version := "0.1"

scalaVersion := "2.12.13"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.2.8",
  "com.typesafe.akka" %% "akka-stream" % "2.6.14",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.2.8" % Test
)

