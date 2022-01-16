package com.sillycat.performance.base

import com.typesafe.config.ConfigFactory

trait PerformanceTesterEnvironment {

  val config = ConfigFactory.load
  val env = "env." + config.getString("build.env")
  private def path(s: String*) = s.mkString(".")

  def envStr(s: String): String = {
    path(env, s)
  }

  val rampSeconds = 1
  val rampNumOfUsers = config.getInt(envStr("ramp.user.num"))
  val requestsPerUser = config.getInt(envStr("requests.per.user"))
  val epjHost = config.getString(envStr("epj.host"))

}
