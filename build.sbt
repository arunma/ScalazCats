name := "Workbench"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  Seq(
    "org.scalaz" %% "scalaz-core" % "7.2.4",
    "org.scalaz" %% "scalaz-effect" % "7.2.4",
    "org.scalaz" %% "scalaz-concurrent" % "7.2.4"
  )
}