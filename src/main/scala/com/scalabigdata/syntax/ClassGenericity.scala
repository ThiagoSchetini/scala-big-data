package com.scalabigdata.syntax



class ClassGenericity[T] {

  /*
    _ represents null for all object types
   */
  private var contents: T = _

  def set(value: T){contents = value}
  def get(): T = contents

}



object TestGenericity extends App {

  val obj = new ClassGenericity[Int]
  obj.set(999)
  println(obj.get())

  val obj2 = new ClassGenericity[Boolean]
  println(obj2.get())

}
