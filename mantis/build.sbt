name := "mantis"

val sparkVersion = "2.4.5"
val breezeVersion = "1.0"

lazy val root = (project in file(".")).settings(
  resolvers ++= Seq(
    "Typesafe" at "http://repo.typesafe.com/typesafe/releases/",
    "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
    "Central" at "https://repo1.maven.org/maven2"
  ),
  version := "0.1-SNAPSHOT",
  organization := "pl.tomekl007",
  scalacOptions in Compile ++= Seq(
    "-encoding", "UTF-8",
    "-target:jvm-1.8",
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xlog-reflective-calls",
    "-Xlint"),
  javacOptions in Compile ++= Seq(
    "-source", "11",
    "-target", "11",
    "-Xlint:unchecked",
    "-Xlint:deprecation"),
  scalaVersion := "2.12.10",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.1.1" % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % "test",
    "org.scalanlp" %% "breeze" % breezeVersion,
    "org.scalanlp" %% "breeze-natives" % breezeVersion,
    "org.scalanlp" %% "breeze-viz" % breezeVersion,
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion,
    "org.apache.spark" %% "spark-mllib" % sparkVersion,
    "mysql" % "mysql-connector-java" % "8.0.15",
    "com.typesafe" % "config" % "1.3.3",
    "commons-logging" % "commons-logging" % "1.2",
    "edu.stanford.nlp" % "stanford-corenlp" % "4.0.0",
    "edu.stanford.nlp" % "stanford-parser" % "3.9.2"
  ),

  libraryDependencies ++= Seq(("org.slf4j" % "slf4j-log4j12" % "1.7.26")
    .excludeAll(ExclusionRule(organization = "log4j"))),
  libraryDependencies += "log4j" % "log4j" % "1.2.17" % "test"
)