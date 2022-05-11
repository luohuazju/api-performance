package com.sillycat.performance.kotlinstarter

import com.sillycat.performance.base.PerformanceTesterEnvironment

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

class CustomerPostConcurrentLockAPISimulation extends Simulation with PerformanceTesterEnvironment {

  val httpConf = http
    .baseUrl(kotlinStarterHost)
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

  val feederRandom = Iterator.continually {
    Map(
      "id" -> s"${Random.alphanumeric.take(25).mkString}",
      "firstName" -> s"${Random.alphanumeric.take(5).mkString}",
      "lastName" -> s"${Random.alphanumeric.take(5).mkString}",
      "status" -> "ACTIVE"
    )
  }

  val customerPostConcurrentLock = exec(http("customerPostConcurrentLock")
    .post("/customers/concurrentlock")
    .headers(headers)
    .body(StringBody("""{
                           "id": "${id}",
                           "firstName": "${firstName}",
                           "lastName": "${lastName}",
                           "status": "ACTIVE"
                       } """))
    .check(status.is(200))
    .check(bodyString.exists))

  val multiScn = scenario("Kotlin Starter with Concurrent Lock")
    .repeat(requestsPerUser){ feed(feederRandom).exec(customerPostConcurrentLock) }

  setUp(
    multiScn.inject(rampUsers(rampNumOfUsers).during(5))
  ).protocols(httpConf)

}