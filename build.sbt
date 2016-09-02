
lazy val macro = (project in(file("macros"))).
  settings(
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value
    )
  )

lazy val testLib = (crossProject in(file("testlib"))).
  settings (
    scalaVersion := "2.11.8"
  )

lazy val testLibJS = testLib.js
lazy val testLibJVM = testLib.jvm

lazy val test = (crossProject in(file("."))).
  settings (
    scalaVersion := "2.11.8",
    libraryDependencies += "macro" %% "macro" % "0.1-SNAPSHOT" % "provided"
  ).dependsOn(testLib)

lazy val testJS = test.js
lazy val testJVM = test.jvm
