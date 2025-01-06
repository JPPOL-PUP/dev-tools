//import sbt._
//import sbt.Keys._
//
//object PupScalafmtPlugin extends AutoPlugin {
//  override def trigger = allRequirements
//  override def requires = plugins.JvmPlugin
//
//  object autoImport {
//    val scalafmtGenerateConfig = taskKey[Unit]("Generate Scalafmt configuration")
//  }
//  import autoImport._
//
//  override def projectSettings: Seq[Def.Setting[_]] = Seq(
//    scalafmtGenerateConfig := {
//      IO.write(
//        file("src/lint/.scalafmt-common.conf"),
//        """version = 3.8.2
//          |project.git = true
//          |indent.defnSite = 2
//          |trailingCommas = multiple
//          |docstrings.style = SpaceAsterisk
//          |docstrings.wrap = yes
//          |maxColumn = 78
//          |indentYieldKeyword = false
//          |
//          |align.preset = more
//          |align.multiline = true
//          |align.stripMargin = true
//          |""".stripMargin
//      )
//    },
//    (Compile / compile) := ((Compile / compile) dependsOn scalafmtGenerateConfig).value
//  )
//}

import sbt._
object PupScalafmtPlugin extends AutoPlugin {
  override def trigger = allRequirements
  override def requires = plugins.JvmPlugin
  override def buildSettings: Seq[Def.Setting[_]] = {
    SettingKey[Unit]("scalafmtGenerateConfig") :=
      IO.write(
        // writes to file once when build is loaded
        file("src/lint/.scalafmt-common.conf"),
        """version = 3.8.2
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
          |""".stripMargin
      )
  }
}