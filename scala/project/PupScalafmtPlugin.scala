package dk.jppol.pup

import sbt._
object PupScalafmtPlugin extends AutoPlugin {
  override def trigger                            = allRequirements
  override def requires                           = plugins.JvmPlugin

  println("PupScalafmtPlugin enabled")

  override def buildSettings: Seq[Def.Setting[_]] = {
    SettingKey[Unit]("scalafmtGenerateConfig") :=
      IO.write(
        file("src/scalafmt/.scalafmt-common.conf"),
        """version = 3.9.2
          |runner.dialect = scala3
          |project.git = true
          |indent.defnSite = 2
          |trailingCommas = multiple
          |docstrings.style = SpaceAsterisk
          |docstrings.wrap = yes
          |maxColumn = 82
          |indentYieldKeyword = false
          |
          |align.preset = more
          |align.multiline = true
          |align.stripMargin = true
          |""".stripMargin,
      )
  }
}
