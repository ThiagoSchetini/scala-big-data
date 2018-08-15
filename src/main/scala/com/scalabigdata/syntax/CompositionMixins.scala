package com.scalabigdata.syntax


trait AbstractA {
  val message: String
}

class ClassB extends AbstractA {
  override val message: String = "i'm a instance of ClassB"
}

trait AbstractC extends AbstractA {
  val loudMessage: String = message.toUpperCase
}

/**
  * ClassD has a superclass "ClassB" and a mixin "AbstractC"
  * classes can only have ONE superclass (extends), but many mixins (with)
  */
class ClassD extends ClassB with AbstractC



object TestCompositionsMixins extends App {
  val d = new ClassD
  println(d.message)
  println(d.loudMessage)
}