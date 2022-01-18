package com.sillycat.performance.epjapi

import com.sillycat.performance.base.PerformanceTesterEnvironment
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class EPJGetAPISimulation extends Simulation with PerformanceTesterEnvironment {

  val httpConf = http
    .baseUrl(epjHost)
    .acceptHeader("application/json, text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val headers =
    Map("Accept" -> "application/json, text/javascript, */*; q=0.01",
      "Keep-Alive" -> "180",
      "X-Requested-With" -> "XMLHttpRequest",
      "Content-type" -> "application/json")

  val jobTitleDescJSONFeed = jsonFile("data/job.title.age.json").random

  val performanceGetWithQuery = exec(http("performanceGetWithQuery")
    .get("/api/v1/performance/get")
    .headers(headers)
    .queryParamMap(Map("title"->"${title}","age" -> "${age}"))
    .check(status.is(200))
    .check(bodyString.exists))

  val multiScn = scenario("EPJ Get")
    .repeat(requestsPerUser){ feed(jobTitleDescJSONFeed).exec(performanceGetWithQuery) }

  setUp(
    multiScn.inject(rampUsers(rampNumOfUsers).during(5))
  ).protocols(httpConf)

}
