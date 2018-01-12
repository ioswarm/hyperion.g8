lazy val settings = Seq(
  name := "$name$"
  , organization := "$organization$"
  , version := "$version$"
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
    libraryDependencies ++= Seq(
      lib.hyperion
    )
  )

lazy val lib = new {
  object Version {
    val hyperion = "0.2.1"
  }

  val hyperion = "de.ioswarm" %% "hyperion" % Version.hyperion
}
