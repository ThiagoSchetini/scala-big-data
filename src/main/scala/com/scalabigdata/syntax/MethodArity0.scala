package com.scalabigdata.syntax

/**
  * Scala attaches significance to whether or not a method is declared with parentheses
  * It's only applicable to methods of arity-0 (no arguments)
  */
class MethodArity0 {

  private var myObj: String = "Some Object"

  /*
    The methods below are different at compile-time
   */

  // doesn't change state -> no side effects
  // ! may not be called with parentheses
  def noParenthesesMethod = myObj + " with more words"


  // change state -> have side effects
  // can be called with or without parentheses
  def parenthesesMethod(): String = {
    myObj = myObj + " modified"
    myObj
  }

  override def toString: String = myObj
}



object MethodArity0Test extends App {

  val obj = new MethodArity0

  println(obj)
  println(obj.noParenthesesMethod) // may not be called with parentheses, it's right!

  println("no state changed at all")
  println(obj)

  println("changing state")
  println(obj.parenthesesMethod())
  println(obj)

}
