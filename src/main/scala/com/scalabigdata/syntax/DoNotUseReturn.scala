package com.scalabigdata.syntax

class DoNotUseReturn {

  def simpleFunction(x: Int, y: Int): Int = {           // (var: Type): ReturnType = { ... execution block ... }
    val x2 = x * x
    val y2 = y * y
    x2 + y2                                             // do not use return! >>> not good practice!
  }

  def functionWithDefaultValue(x:Int, y:Double = 88.5): Double = {
    if (x == 5)
      x                                                 // do not use return! >>> not good practice!
    else
      x + y                                             // do not use return! >>> not good practice!
  }

  def functionInOneLine(x:Int, y:Double = 88): Double = if (x == 5) x else x + y

}

object DoNotUseReturnTest extends App {

  val obj = new DoNotUseReturn

  println(obj.simpleFunction(5, 5))
  println(obj.functionWithDefaultValue(55))
  println(obj.functionInOneLine(33))

}
