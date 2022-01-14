enablePlugins(GatlingPlugin)

scalaVersion := "2.13.7"

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.7.3" % "test"
libraryDependencies += "io.gatling"            % "gatling-test-framework"    % "3.7.3" % "test"

