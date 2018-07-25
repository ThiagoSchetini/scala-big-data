package com.scalabigdata.syntax



/**
  * protected on Scala has a different meaning fo Java ... see
  */
class ProtectedModifier { protected def execute: (Int, Int) => Int = _ + _ }



/**
  * won't compile, even it is on the same package!
  */
class AccessExternal {
  val obj = new ProtectedModifier
  //obj.execute
}



/**
  * will access the protected one because it is a subclass
  */
class AccessInstance extends ProtectedModifier {
  val x = execute(5, 5)
}



object ProtectedModifierTest extends App {
  val obj = new AccessInstance
  println(obj.x)
}