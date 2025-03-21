import scala.collection.Seq

val githubOrganizationName = "JPPOL-PUP"
val githubProjectName = "dev-tools"

sbtPlugin := true

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
    scalaVersion          := "2.12.17",
    crossSbtVersions      := Seq("1.0", "1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.8", "1.9", "1.10"),
    organizationName      := "JP/Politikens Hus",
    organization          := "dk.jppol",
    scalafmtConfig        := file(s"${baseDirectory.value}/src/scalafmt/.scalafmt.conf"),
    dynverVTagPrefix      := false
  ) ++ publishSettings)

scalafmtOnCompile := true

publishMavenStyle := false

scalafixOnCompile := true