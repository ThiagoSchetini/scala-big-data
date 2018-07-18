package com.scalabigdata.basics

import scala.collection.mutable.ArrayBuffer

trait Pet {
  val name: String
}

class Cat(val name: String) extends Pet

class Dog(val name: String) extends Pet

object Test {
  def main(args: Array[String]): Unit = {

    val cat = new Cat("Larry")
    val dog = new Dog("Greg")

    val animals = ArrayBuffer.empty[Pet]  // using the trait as a reference for subtypes
    animals.append(cat)
    animals.append(dog)

    animals.foreach(a => println(a.name))
  }
}
