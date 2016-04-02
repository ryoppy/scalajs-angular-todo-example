name := """scalajs-sample"""

lazy val root = project.in(file(".")).
  aggregate(js, jvm).
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val app = crossProject.in(file("."))
  .settings(
    version := "1.0",
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core" % "0.3.0",
      "io.circe" %% "circe-generic" % "0.3.0",
      "io.circe" %% "circe-parser" % "0.3.0",
      "io.circe" %% "circe-core_sjs0.6" % "0.3.0",
      "io.circe" %% "circe-generic_sjs0.6" % "0.3.0",
      "io.circe" %% "circe-parser_sjs0.6" % "0.3.0",
      "io.circe" %% "circe-scalajs_sjs0.6" % "0.3.0",
      "com.chuusai" %% "shapeless" % "2.3.0",
      "com.chuusai" %% "shapeless_sjs0.6" % "2.3.0"
    )
  )
  .jvmConfigure(_.enablePlugins(PlayScala))
  .jsSettings(
    skip in packageJSDependencies := false,
    persistLauncher in Compile := true,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.0",
      "biz.enef" %%% "scalajs-angulate" % "0.2.4"
    ),
    jsDependencies += "org.webjars" % "angularjs" % "1.5.3" / "1.5.3/angular.js",
    mainClass in(Compile, run) := Some("app.Bootstrap")
  )
  .configure { p =>
    val outputSettings = Seq(fastOptJS, fullOptJS, packageJSDependencies).map { packageJSKey =>
      crossTarget in(p.js, Compile, packageJSKey) := (baseDirectory in Compile).value / "public" / "javascripts"
    }
    p.jvmSettings(outputSettings: _*)
  }



lazy val jvm = app.jvm
lazy val js = app.js
