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
    emitSourceMaps := false,
    persistLauncher := true,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.0",
      "biz.enef" %%% "scalajs-angulate" % "0.2.4"
    ),
    jsDependencies ++= Seq(
      "org.webjars.bower" % "angular" % "1.5.3" / "1.5.3/angular.js",
      "org.webjars.bower" % "angular-route" % "1.5.3" / "1.5.3/angular-route.js"
    )
  )
  .configure { p =>
    val outputSettings = Seq(fastOptJS, fullOptJS, packageJSDependencies, packageScalaJSLauncher).map { packageJSKey =>
      crossTarget in(p.js, Compile, packageJSKey) := (baseDirectory in Compile).value / "public" / "javascripts"
    }
    p.jvmSettings(outputSettings: _*)
  }

lazy val jvm = app.jvm
lazy val js = app.js
