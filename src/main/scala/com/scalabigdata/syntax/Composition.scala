package com.scalabigdata.syntax

import scala.collection.mutable.ArrayBuffer

trait Pet {
  val name: String
}

class Cat(val name: String) extends Pet

class Dog(val name: String) extends Pet

object PetTest extends App {

  val cat = new Cat("Larry")
  val dog = new Dog("Greg")

  val animals = ArrayBuffer.empty[Pet]  // using the trait as a reference for subtypes
  animals.append(cat)
  animals.append(dog)

  animals.foreach(a => println(a.name))

}
