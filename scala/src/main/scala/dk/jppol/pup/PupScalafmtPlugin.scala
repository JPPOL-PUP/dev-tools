package dk.jppol.pup

import sbt._
import Keys._
import org.scalafmt.sbt.ScalafmtPlugin
import org.scalafmt.sbt.ScalafmtPlugin.autoImport._

object PupScalafmtPlugin extends AutoPlugin {
  override def trigger  = allRequirements
  override def requires = plugins.JvmPlugin

  object autoImport {
    val pupScalafmtGenerateConfig = taskKey[Unit]("Generate scalafmt config file")
  }
  import autoImport._

  private def ensureConfigFileExists(baseDir: File): File = {
    val confFile = baseDir / ".scalafmt.conf"
    if (!confFile.exists()) {
      IO.write(
        confFile,
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
    confFile
  }

  override val projectSettings: Seq[Def.Setting[_]] = Seq(
    scalafmtConfig            := {
      val baseDir = (ThisBuild / baseDirectory).value
      ensureConfigFileExists(baseDir)
    },
    pupScalafmtGenerateConfig := {
      val baseDir = (ThisBuild / baseDirectory).value
      ensureConfigFileExists(baseDir)
      println(s"Scalafmt config created at ${baseDir / ".scalafmt.conf"}")
    },
  )
}
