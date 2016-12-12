import play.sbt.PlayScala
import play.sbt.routes.RoutesKeys._
import sbt.Keys._
import sbt._

object BuildCommon {

  val versionScala = "2.11.8"
  val versionPlay = "2.5.8"
  val versionAkka = "2.4.14"

  val dependenciesResolvers = DefaultOptions.resolvers(snapshot = true) ++ Seq(
    "scalaz-releases" at "http://dl.bintray.com/scalaz/releases"
  )

  val scalaBuildOptions = Seq("-unchecked", "-deprecation", "-feature", "-language:reflectiveCalls",
    "-language:implicitConversions", "-language:postfixOps", "-language:dynamics","-language:higherKinds",
    "-language:existentials", "-language:experimental.macros", "-Xmax-classfile-name", "140")

  val buildSettings = Seq(
    organization := "org.nico",
    scalaVersion := versionScala,
    resolvers ++= dependenciesResolvers,
    fork in test := true,
    fork in run := true,
    connectInput in run := true,
    parallelExecution in Test := true,
    scalacOptions ++= scalaBuildOptions,
    javaOptions ++= Seq("-Xmx4086M", "-Xms512M")
  )

  def Module(name: String): Project = Project(name, file(s"$name")).settings(buildSettings: _*)

  def PlayModule(name: String): Project = Project(name, file(s"$name")).settings(buildSettings: _*)
    .enablePlugins(PlayScala).settings(routesGenerator := InjectedRoutesGenerator)

}
