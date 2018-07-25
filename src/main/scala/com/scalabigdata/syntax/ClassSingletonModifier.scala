package com.scalabigdata.syntax


/**
  * object is a singleton modifier!
  */
object MySingleton {
  var anything:String = "original string"
  override def toString: String = "I'm the singleton !"
}

object MySingletonTest extends App {

  /*
    it is the same object and don't need to be instanciated
   */
  val reference = MySingleton
  println(reference.anything)

  /*
    still the same object!
   */
  reference.anything = "modified phrase"
  val anotherReference = MySingleton
  println(anotherReference.anything)

}
