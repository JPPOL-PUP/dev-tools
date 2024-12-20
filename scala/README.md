# Publication Platform Scala Lint Format

This project is an SBT plugin that integrates Scalafmt and Scalafix for code formatting and linting in Scala projects.

## Features

- **Scalafmt**: Automatically formats your Scala code.
- **Scalafix**: Provides linting and refactoring rules for your Scala code.

## Installation

1. **Add the plugin to your project:**

   Add the following to your `project/plugins.sbt` file:
   ```scala
   addSbtPlugin("your.organization" % "publication-platform-scala-lint-format" % "1.0-SNAPSHOT")