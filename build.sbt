import Dependencies._

ThisBuild / scalaVersion     := "2.13.7"
ThisBuild / version          := "0.0.1-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "day01"

lazy val root = (project in file("."))
  .settings(
    name := "advent-of-code-2022",
    libraryDependencies += scalaTest % Test
  )
