# Publication Platform Scala Lint Format

This project is an SBT plugin that integrates Scalafmt and Scalafix for code formatting and linting in Scala projects following standards of the Publication Platform team.

## Features

- **Scalafmt**: Automatically formats Scala code.
- **Scalafix**: Provides linting and refactoring rules for Scala code.

## Installation

1. **Add the plugin to your project:**

   Add the following to your `project/plugins.sbt` file:
   ```scala
   addSbtPlugin("dk.jppol" % "scala-lint-format" % "X.X.X")

2. **Run sbt task to generate config files for scalafix and scalafmt**

   ```bash
   sbt generateAllConfigs
   ```
   
3. **Now you can run scalafix and scalafmt locally**
   ```bash
   sbt scalafixAll
   sbt scalafmt
   sbt scalafmtCheck
   ```
   etc.

4. **Add to CI pipeline**

   Workflow at https://github.com/JPPOL-PUP/shared-workflows/blob/main/.github/workflows/sbt-run-scalafmt-scalafix.yml