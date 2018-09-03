name := "ScalazCats"

version := "1.0"

scalaVersion in ThisBuild := "2.12.6"
scalacOptions in ThisBuild ++= Seq(
  "-language:_", //  "-language:higherKinds" etc
  "-Ypartial-unification",
  "-Xfatal-warnings",
)

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.2.0",
  "com.github.mpilquist" %% "simulacrum"     % "0.13.0",
  "org.scalaz"           %% "scalaz-core"    % "7.2.26"
)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.7")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)