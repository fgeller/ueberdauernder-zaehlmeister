name := "ueberdauernder-zaehlmeister"

version := "1.0"

scalaVersion := "2.11.0"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.1.3" % "test"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.2"

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.3.2"

incOptions := incOptions.value.withNameHashing(true)
