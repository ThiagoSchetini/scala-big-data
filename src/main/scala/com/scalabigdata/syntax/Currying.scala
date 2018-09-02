package com.scalabigdata.syntax


class Currying {

  /**
    * this is a functions that returns a new function, it's called currying
    *
    * it could be: def mul(x: Int, y: Int) = x * y
    */
  def mulOneAtTime(x: Int) = (y: Int) => x * y


  /**
    * There is a shortcut for defining such curried function in Scala =)
    */
  def mulOneAtTimeOnScala(x: Int)(y: Int) = x * y

}


object CurryingTest extends App {

  val cur = new Currying
  val result = cur.mulOneAtTimeOnScala(5)(4)
  println(result)

}