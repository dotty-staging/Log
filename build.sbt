lazy val baseName  = "Log"
lazy val baseNameL = baseName.toLowerCase

lazy val projectVersion = "0.1.1"
lazy val mimaVersion    = "0.1.0"

lazy val commonJvmSettings = Seq(
  crossScalaVersions := Seq("3.0.0-M3", "2.13.4", "2.12.12"),
)

// sonatype plugin requires that these are in global
ThisBuild / version      := projectVersion
ThisBuild / organization := "de.sciss"

lazy val root = crossProject(JSPlatform, JVMPlatform).in(file("."))
  .jvmSettings(commonJvmSettings)
  .settings(
    name               := baseName,
//    version            := projectVersion,
//    organization       := "de.sciss",
    scalaVersion       := "2.13.4",
    description        := "A very simple Scala logging library that works with JVM and JS, Scala 2 and 3",
    homepage           := Some(url(s"https://github.com/Sciss/${name.value}")),
    licenses           := Seq("LGPL v2.1+" -> url("http://www.gnu.org/licenses/lgpl-2.1.txt")),
    mimaPreviousArtifacts := Set("de.sciss" %% baseNameL % mimaVersion),
    scalacOptions ++= Seq(
      "-deprecation", "-unchecked", "-feature", "-encoding", "utf8", "-Xlint", "-Xsource:2.13",
    ),
    scalacOptions in (Compile, compile) ++= {
      val jdkGt8  = scala.util.Properties.isJavaAtLeast("9")
      val sv      = scalaVersion.value
      val dot     = isDotty.value  // https://github.com/lampepfl/dotty/issues/8634
      val sq0     = (if (!dot && jdkGt8) List("-release", "8") else Nil)
      if (sv.startsWith("2.12.")) sq0 else "-Wvalue-discard" :: sq0
    }, // JDK >8 breaks API; skip scala-doc
  )
  .settings(publishSettings)

// ---- publishing ----
lazy val publishSettings = Seq(
  publishMavenStyle := true,
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  developers := List(
    Developer(
      id    = "sciss",
      name  = "Hanns Holger Rutz",
      email = "contact@sciss.de",
      url   = url("https://www.sciss.de")
    )
  ),
  scmInfo := {
    val h = "git.iem.at"
    val a = s"sciss/${name.value}"
    Some(ScmInfo(url(s"https://$h/$a"), s"scm:git@$h:$a.git"))
  },
)

