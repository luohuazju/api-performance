enablePlugins(GatlingPlugin)

scalaVersion := "2.13.7"

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.7.3" % "test"
libraryDependencies += "io.gatling"            % "gatling-test-framework"    % "3.7.3" % "test"

