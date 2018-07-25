package com.scalabigdata.syntax

class FunctionalScala {

  def funcFunctional: (Int, Int) => Int = (x, y) => if (x == 5) x else x + y
  // it's called anonymous
  // can be assigned with def, val, var ...

  def anonymous: Int => Int = x => x + 1                // anonymous function: Type => ReturnType = ... execution

  def weirdUnderline: Int => Int = _ + 1                // " _ " for a parameter used only one time
  // (... x => x...) turn on _

  def moreUnderline: (Int, Int, Double) => Double = _ + _ + _
  // first, second and third parameters represented as _

  def anonymousWithBlock: (Int, Int) => Int = { (x, y) =>
    if (x == 5)
      x
    else
      x + y
  }

}

object FunctionalScalaTest extends App {

  val obj = new FunctionalScala

  println(obj.anonymous(4))
  println(obj.weirdUnderline(5))
  println(obj.moreUnderline(4, 5, 4.43))
  println(obj.anonymousWithBlock(4, 5))

}
