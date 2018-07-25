package com.scalabigdata.syntax



class Callbacks {

  def noReturnFunction(callback: () => Unit): Unit = callback()
  // executing a function received as parameter

  def anotherNoReturnFunction(optionalCallback: Option[() => Unit] = None): Unit = {
    optionalCallback.map(c => c())
  }

  def callbackWithParam(callback: Double => Double): String = {
    val internalResult:Double = callback(9.99)
    "returning the result >>> " + internalResult
  }

  def callbackWithParams(callbackWithParams: (Double, Int) => Double): String = {
    val internalDouble: Double = 3.33
    val internalInt: Int = 10
    val internalResult:Double = callbackWithParams(internalDouble, internalInt)
    "returning the result >>> " + internalResult
  }

  def execute(callback: (Any, Any) => Any, x: Any, y: Any): Any = callback(x, y)

}



class CallbackTimer(message: String) {

  private def twoPerSecond(callback: () => Unit) = for(i <- 1 to 4) { callback(); Thread sleep 500}

  def execute = twoPerSecond(() => println("timer: " + message))

}



object CallbacksTest extends App {

/**
  * Testing all methods!
  */

  val callbacks = new Callbacks

  callbacks.noReturnFunction(() => println("i'm a anonymous function been executed >>> " + (7 + 7)))
  // an anonymous function is a parameter of someFunction

  callbacks.anotherNoReturnFunction(Some(() =>
    println("anonymous been executed >>> " + (5 + 5))
  ))

  println(callbacks.callbackWithParam((x: Double) => x + x))

  println(callbacks.callbackWithParams((x: Double, y:Int) => x + y))

  val anything = 5543.23
  val anythink = "nothing else matters"
  val result = callbacks.execute(
    (a: Any, b: Any) => println("printing objects >>> " + a + ", " + b),
    anything,
    anythink
  )

  new CallbackTimer("Go Horse !!!").execute

}