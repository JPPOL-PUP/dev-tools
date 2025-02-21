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
  .enablePlugins(PupScalafmtPlugin)
  .settings( Seq(
    name                  := "scala-lint-format",
    description           := "Configuration package for scalafmt/scalafix",
    scalaVersion          := "3.3.1",
    organizationName      := "JP/Politikens Hus",
    organization          := "dk.jppol",
    scalafmtConfig        := file(s"${baseDirectory.value}/src/scalafmt/.scalafmt.conf"),
    dynverVTagPrefix      := false
  ) ++ publishSettings)

scalafmtOnCompile := true

publishMavenStyle := true

scalafmtOnCompile := true

scalafixOnCompile := true