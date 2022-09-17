import Dependencies._

ThisBuild / scalaVersion     := "2.13.10"
ThisBuild / version          := "0.0.1-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "advent-of-code-2022",
    libraryDependencies += scalaTest % Test
  )
