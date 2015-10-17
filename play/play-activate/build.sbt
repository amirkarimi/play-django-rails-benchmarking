name := """play-activate"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.scalikejdbc"     %% "scalikejdbc-async"             % "0.5.+",
  "org.scalikejdbc"     %% "scalikejdbc-async-play-plugin" % "0.5.+",
  "com.github.mauricio" %% "postgresql-async"              % "0.2.+"
)
