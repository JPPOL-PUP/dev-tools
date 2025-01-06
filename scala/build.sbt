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
  .settings(
    name                  := "publication-platform-scala-lint-format",
    description           := "Generate Scalafmt configuration package",
    version               := "1.0-SNAPSHOT",
    scalaVersion          := "3.3.1",
    organizationName      := "JP/Politikens Hus",
    organization          := "dk.jppol",
    scalafmtConfig        := file("src/lint/.scalafmt-common.conf"),
  )
  .settings(publishSettings: _*)


publishMavenStyle := true

scalafmtOnCompile := true

scalafixOnCompile := true