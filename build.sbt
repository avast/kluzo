lazy val scalaSettings = Seq(
  scalaVersion := "2.11.8",
  scalacOptions += "-deprecation",
  scalacOptions += "-unchecked",
  scalacOptions += "-feature",
  crossScalaVersions := Seq("2.11.8", "2.12.4"),
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
)

lazy val javaSettings = Seq(
  crossPaths := false,
  autoScalaLibrary := false,
  crossScalaVersions := Seq("2.11.8") // it's not really used; it's just about turning-off the crosscompilation
)

lazy val Versions = new {
  val slf4j = "1.7.25"
}

lazy val commonSettings = Seq(
  organization := "com.avast.kluzo",
  version := sys.env.getOrElse("TRAVIS_TAG", "0.1-SNAPSHOT"),
  description := "Library for passing tracing ID between threads in multi-threaded applications",

  licenses ++= Seq("MIT" -> url(s"https://github.com/avast/kluzo/blob/${version.value}/LICENSE")),
  publishArtifact in Test := false,
  publishArtifact in(Compile, packageDoc) := false,
  sources in(Compile, doc) := Seq.empty,
  bintrayOrganization := Some("avast"),
  bintrayPackage := "kluzo",

  pomExtra := (
    <scm>
      <url>git@github.com:avast/kluzo.git</url>
      <connection>scm:git:git@github.com:avast/kluzo.git</connection>
    </scm>
      <developers>
        <developer>
          <id>avast</id>
          <name>Jakub Janecek, Avast Software s.r.o.</name>
          <url>https://www.avast.com</url>
        </developer>
      </developers>
    ),
  resolvers += Resolver.jcenterRepo,
  libraryDependencies ++= Seq(
    "junit" % "junit" % "4.12" % "test",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "com.novocode" % "junit-interface" % "0.10" % "test", // Required by sbt to execute JUnit tests
    "ch.qos.logback" % "logback-classic" % "1.1.8" % "test"
  ),
  testOptions += Tests.Argument(TestFrameworks.JUnit)
)

lazy val root = (project in file("."))
  .settings(
    name := "kluzo",
    publish := {},
    publishLocal := {}
  ).aggregate(coreScala, core)

lazy val coreScala = (project in file("kluzo-scala")).
  settings(
    commonSettings,
    scalaSettings,
    name := "kluzo-scala",

    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % Versions.slf4j,
      "com.avast.continuity" %% "continuity-core" % "3.0.0"
    )
  )

lazy val core = (project in file("kluzo")).
  settings(
    commonSettings,
    scalaSettings,
    name := "kluzo",
    libraryDependencies ++= Seq(
    )
  ).dependsOn(coreScala)
