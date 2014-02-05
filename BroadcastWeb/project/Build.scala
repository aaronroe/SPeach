import sbt._
import Keys._
import play.Project._

object Build extends sbt.Build {

  val appName         = "SPeach"
  val appVersion      = "0.0-SNAPSHOT"

  val appDependencies = Seq(
    javaCore,
    javaJdbc,
    javaEbean,
    "org.atmosphere" % "atmosphere-play" % "1.2.0"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers ++= Seq(
      "Maven Repository" at "http://repo1.maven.org/maven2/",
      "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
    )
  )

}
