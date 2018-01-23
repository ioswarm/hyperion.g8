lazy val settings = Seq(
  name := "example"
  , organization := "com.example"
  , version := "0.1.0.SNAPSHOT"
  , scalaVersion := "2.12.4"
  , scalacOptions ++= Seq(
    "-language:_"
    , "-unchecked"
    , "-deprecation"
    , "-encoding", "UTF-8"
  )
)

lazy val root = project.in(file("."))
  .settings(settings)
  .settings(
    mainClass in assembly := Some("Main")
    ,libraryDependencies ++= Seq(
      lib.hyperion
    )
  )

lazy val lib = new {
  object Version {
    val hyperion = "0.2.1"
  }

  val hyperion = "de.ioswarm" %% "hyperion-core" % Version.hyperion
}
