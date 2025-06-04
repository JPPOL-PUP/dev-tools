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
    val pupScalafixGenerateConfig = taskKey[Unit]("Generate scalafix config file")
    val pupGenerateAllConfigs     =
      taskKey[Unit]("Generate scalafmt and scalafix config files")
  }
  import autoImport._

  private def ensureScalafmtConfigExists(baseDir: File): File = {
    val confFile = baseDir / ".scalafmt.conf"
    if (!confFile.exists()) {
      IO.write(confFile, scalafmtConfigContent)
      println(s"[PUP] Created scalafmt config at $confFile")
    }
    confFile
  }

  private def ensureScalafixConfigExists(baseDir: File): File = {
    val confFile = baseDir / ".scalafix.conf"
    if (!confFile.exists()) {
      IO.write(confFile, scalafixConfigContent)
      println(s"[PUP] Created scalafix config at $confFile")
    }
    confFile
  }

  private val scalafmtConfigContent =
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

  private val scalafixConfigContent =
    """rules = [
      |  OrganizeImports,
      |  DisableSyntax,
      |  LeakingImplicitClassVal,
      |  NoValInForComprehension
      |]
      |
      |OrganizeImports.groupedImports = Merge
      |OrganizeImports.expandRelative = true
      |OrganizeImports.removeUnused = true
      |OrganizeImports.groups = [
      |  "re:javax?\\."
      |  "scala."
      |  "*"
      |  "re:^\\w+\\."
      |]
      |
      |DisableSyntax.noVars = true
      |DisableSyntax.noThrows = true
      |DisableSyntax.noNulls = true
      |DisableSyntax.noReturns = true
      |DisableSyntax.noWhileLoops = true
      |DisableSyntax.noAsInstanceOf = false
      |DisableSyntax.noIsInstanceOf = false
      |DisableSyntax.noXml = true
      |""".stripMargin

  override val projectSettings: Seq[Def.Setting[_]] = Seq(
    scalafmtConfig            := {
      val baseDir = (ThisBuild / baseDirectory).value
      ensureScalafmtConfigExists(baseDir)
    },
    pupScalafmtGenerateConfig := {
      val baseDir = (ThisBuild / baseDirectory).value
      ensureScalafmtConfigExists(baseDir)
    },
    pupScalafixGenerateConfig := {
      val baseDir = (ThisBuild / baseDirectory).value
      ensureScalafixConfigExists(baseDir)
    },
    pupGenerateAllConfigs     := {
      val baseDir = (ThisBuild / baseDirectory).value
      ensureScalafmtConfigExists(baseDir)
      ensureScalafixConfigExists(baseDir)
      println(s"[PUP] Generated all config files in $baseDir")
    },
  )
}
