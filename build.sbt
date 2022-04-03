ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

name := "zio-sandbox"

val zioVersion = "1.0.13"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % zioVersion,
  "org.scalatest" %% "scalatest" % "3.2.11" % Test,
  "dev.zio" %% "zio-test"     % zioVersion % Test,
  "dev.zio" %% "zio-test-sbt" % zioVersion % Test
)

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")