package com.scalabigdata.syntax



/**
  * override equals using all properties
  * override hashcode using all properties
  * override toString using propertie values
  * parameters become properties (val) with getters and setters
  * do not need new to create an object
  * can use convenient extractors in match/case statements
  */
case class ClassCaseModifier(someNumber:Int, anotherNumber:Double)



object ClassCaseModifierTest extends App {
  val obj = ClassCaseModifier(22, 33.33)
  println(obj)

  val obj2 = ClassCaseModifier(55, 99.99)
  println(obj.equals(obj2)) // false
  println(obj2)

  val obj3 = ClassCaseModifier(22, 33.33)
  println(obj.equals(obj3)) // true
  println(obj3)

  println(obj.hashCode())   // same hash as obj3
  println(obj2.hashCode())
  println(obj3.hashCode())  // same hash as obj1
}