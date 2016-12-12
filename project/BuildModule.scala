import sbt._
import sbt.Keys._

object BuildModule extends Build {

  import BuildCommon._

  // ===== Dependencies =====
//  val scalaConfig   = "com.typesafe"                %   "config"        % "1.3.1"
//  val scalaLogging  = "com.typesafe.scala-logging"  %%  "scala-logging" % "3.5.0"
  val scalaTest     = "org.scalatest"           %%  "scalatest"                  % "2.2.6" % "test"
//  val testMock      = "org.scalamock"           %%  "scalamock-scalatest-support" % "3.2.2" % "test"
//
//  val akkaActor     = "com.typesafe.akka" %%  "akka-actor"  % versionAkka
//  val akkaSlf4j     = "com.typesafe.akka" %%  "akka-slf4j"  % versionAkka
//
//  val play          = "com.typesafe.play" %%  "play"            % versionPlay
//  val playWS        = "com.typesafe.play" %%  "play-ws"         % versionPlay
//  val playCache     = "com.typesafe.play" %%  "play-cache"      % versionPlay
//  val playFilters   = "com.typesafe.play" %%  "filters-helpers" % versionPlay
//  val playTest      = "org.scalatestplus.play"  %%  "scalatestplus-play"         % "1.5.0" % "test"


  // ===== Modules =====
  lazy val simpleTextSearch = Module("simple-text-search").settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(scalaTest)
  )

  // ===== Root =====
  lazy val root = project.in(file(".")).aggregate(
    simpleTextSearch
  )

}
