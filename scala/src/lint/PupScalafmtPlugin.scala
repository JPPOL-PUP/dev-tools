import sbt._
object PupScalafmtPlugin extends AutoPlugin {
  override def trigger = allRequirements
  override def requires = plugins.JvmPlugin
  override def buildSettings: Seq[Def.Setting[_]] = {
    SettingKey[Unit]("scalafmtGenerateConfig") :=
      IO.write(
        // writes to file once when build is loaded
        file(".scalafmt-common.conf"),
        """version = 3.8.2
          |project.git = true
          |indent.defnSite = 2
          |trailingCommas = multiple
          |docstrings.style = SpaceAsterisk
          |docstrings.wrap = yes
          |maxColumn = 78
          |indentYieldKeyword = false
          |
          |align.preset = more
          |align.multiline = true
          |align.stripMargin = true
          |""".stripMargin
      )
  }
}
