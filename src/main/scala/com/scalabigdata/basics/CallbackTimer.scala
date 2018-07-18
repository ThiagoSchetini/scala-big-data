package com.scalabigdata.basics

object CallbackTimer {

  // Thread.sleep(1000) could be Thread sleep 1000
  def oncePerSecond(callback: () => Unit) = while(true) { callback(); Thread.sleep(1000)}

  def timeFlies() = println("the time flies my love")

  def main(args: Array[String]) = oncePerSecond(timeFlies)

}
