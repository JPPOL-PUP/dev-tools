import scala.collection.Seq

val githubOrganizationName = "JPPOL-PUP"
val githubProjectName = "dev-tools"


lazy val publishSettings = Seq(
  publishTo := Some(s"GitHub Package Registry ($githubProjectName)" at s"https://maven.pkg.github.com/$githubOrganizationName/$githubProjectName"),
  credentials ++= {
    val githubToken = System.getenv("GITHUB_TOKEN")
    if (githubToken == null) Seq.empty
    else Seq(Credentials("GitHub Package Registry", "maven.pkg.github.com", "_", githubToken))
  }
)

lazy val root = project
  .in(file("."))
  .settings(Seq(
    sbtPlugin             := true,
    name                  := "scala-lint-format",
    description           := "Configuration package for scalafmt/scalafix",
    scalaVersion          := "2.12.18",
    organizationName      := "JP/Politikens Hus",
    organization          := "dk.jppol"
  ) ++ publishSettings)

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.2")

scalafmtOnCompile := true

publishMavenStyle := true

//scalafixOnCompile := true