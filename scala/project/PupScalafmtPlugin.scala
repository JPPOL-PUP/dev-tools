package dk.jppol.pup

import sbt._
import Keys._
import org.scalafmt.sbt.ScalafmtPlugin
import org.scalafmt.sbt.ScalafmtPlugin.autoImport._

object PupScalafmtPlugin extends AutoPlugin {

  println("[DEBUG] PupScalafmtPlugin object is being initialized!")

  override def trigger = allRequirements
  override def requires = plugins.JvmPlugin

  object autoImport {
    val pupScalafmtGenerateConfig = taskKey[Unit]("Generate the standard scalafmt config file")
  }
  import autoImport._

  private def ensureConfigFileExists(baseDir: File): File = {
    println(s"[DEBUG] ensureConfigFileExists called with baseDir: $baseDir")
    val confFile = baseDir / ".scalafmt.conf"
    if (!confFile.exists()) {
      println(s"[DEBUG] Creating scalafmt config file at: $confFile")
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
          |""".stripMargin
      )
      println(s"[DEBUG] Successfully created scalafmt config file")
    } else {
      println(s"[DEBUG] Scalafmt config file already exists at: $confFile")
    }
    confFile
  }

  private val customSettings: Seq[Def.Setting[_]] = {
    println("[DEBUG] customSettings being evaluated")
    Seq(
      scalafmtConfig := {
        println("[DEBUG] scalafmtConfig setting being evaluated")
        val baseDir = (ThisBuild / baseDirectory).value
        println(s"[DEBUG] scalafmtConfig - baseDir: $baseDir")
        ensureConfigFileExists(baseDir)
      },

      pupScalafmtGenerateConfig := {
        println("[DEBUG] pupScalafmtGenerateConfig task being executed")
        val baseDir = (ThisBuild / baseDirectory).value
        println(s"[DEBUG] pupScalafmtGenerateConfig - baseDir: $baseDir")
        ensureConfigFileExists(baseDir)
        println(s"Scalafmt config created at ${baseDir / ".scalafmt.conf"}")
      }
    )
  }

  override val projectSettings: Seq[Def.Setting[_]] = {
    println("[DEBUG] Plugin is being applied to a project!")
    customSettings
  }

  // Debug: Check if globalSettings is being called
  override def globalSettings: Seq[Def.Setting[_]] = {
    println("[DEBUG] globalSettings being evaluated")
    super.globalSettings
  }
}