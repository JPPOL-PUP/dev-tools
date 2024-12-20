import scala.collection.Seq

name := "publication-platform-scala-lint-format"

version := "1.0-SNAPSHOT"

lazy val `publication-platform-scala-lint-format` = (project in file("."))

scalaVersion := "2.12.13"

publishTo := Some(
  "GitHub Packages" at s"https://maven.pkg.github.com/jppol-pup/github-shared-resources"
)

publishMavenStyle := true

scalafmtOnCompile := true

scalafixOnCompile := true