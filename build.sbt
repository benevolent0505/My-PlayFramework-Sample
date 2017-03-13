name := """my-play-sample"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.postgresql"  %  "postgresql"                   % "42.0.0",
  "org.scalikejdbc" %% "scalikejdbc"                  % "2.5.0",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "2.5.0",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.5.1",

  "org.mindrot" % "jbcrypt" % "0.4",

  "org.webjars" % "bootstrap"      % "3.3.7",
  "org.webjars" % "bootstrap-sass" % "3.3.7",

  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)