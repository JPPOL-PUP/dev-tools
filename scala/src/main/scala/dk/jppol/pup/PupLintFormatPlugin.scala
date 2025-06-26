package dk.jppol.pup

import sbt._
import Keys._
import org.scalafmt.sbt.ScalafmtPlugin
import org.scalafmt.sbt.ScalafmtPlugin.autoImport._

object PupLintFormatPlugin extends AutoPlugin {
  override def trigger  = allRequirements
  override def requires = plugins.JvmPlugin

  object autoImport {
    val scalafmtGenerateConfig = taskKey[Unit]("Generate scalafmt config file")
    val scalafixGenerateConfig = taskKey[Unit]("Generate scalafix config file")
    val generateAllConfigs     =
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

  private def loadResourceAsString(name: String): String =
    IO.readStream(getClass.getResourceAsStream(s"/$name"))

  private lazy val scalafmtConfigContent = loadResourceAsString("scalafmt.conf")
  private lazy val scalafixConfigContent = loadResourceAsString("scalafix.conf")

  override val projectSettings: Seq[Def.Setting[_]] = Seq(
    scalafmtConfig            := {
      val baseDir = (ThisBuild / baseDirectory).value
      ensureScalafmtConfigExists(baseDir)
    },
    scalafmtGenerateConfig := {
      val baseDir = (ThisBuild / baseDirectory).value
      ensureScalafmtConfigExists(baseDir)
    },
    scalafixGenerateConfig := {
      val baseDir = (ThisBuild / baseDirectory).value
      ensureScalafixConfigExists(baseDir)
    },
    generateAllConfigs     := {
      val baseDir = (ThisBuild / baseDirectory).value
      ensureScalafmtConfigExists(baseDir)
      ensureScalafixConfigExists(baseDir)
      println(s"[PUP] Generated all config files in $baseDir")
    },
  )
}
