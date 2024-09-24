ThisBuild / version := "0.1.0"
ThisBuild / organization := "top.davidon"
ThisBuild / licenses ++= Seq(
  "GLWTS" -> url("https://github.com/me-shaon/GLWTPL/blob/master/NSFW_LICENSE"),
)

ThisBuild / scalaVersion := "3.5.1"


lazy val root = (project in file("."))
  .settings(
    name := "monteCarlo",
    assembly / mainClass := Some("top.davidon.montecarlo.main.Main"),
  )
