package dk.jppol.pup

import sbt._
import sbt.Keys._

object PupScalafmtPlugin extends AutoPlugin {

  println("=== PupScalafmtPlugin is being loaded! ===")

  override def trigger = allRequirements
  override def requires = plugins.JvmPlugin

  object autoImport {
    val pupTest = taskKey[Unit]("Test task")
  }
  import autoImport._

  override lazy val projectSettings = Seq(
    pupTest := {
      println("PupScalafmtPlugin is working!")
    }
  )
}