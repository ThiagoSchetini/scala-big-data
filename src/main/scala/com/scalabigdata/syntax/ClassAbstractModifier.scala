package com.scalabigdata.syntax



/**
  * Traits are abstract classes, like interfaces
  */
trait ClassAbstractModifier {
  def save(something: String)
  def update(something: String)
  def delete(something: String)
  def list
}

trait BigData {
  def appendOnDatalake(something: String)
}



object Database extends ClassAbstractModifier with BigData {
  override def save(something: String) = println("simulating a database saving: " + something)
  override def update(something: String) = println("simulating a database update: " + something)
  override def delete(something: String)= println("simulating a database delete: " + something)
  override def appendOnDatalake(something: String) = println("putting something on datalake: " + something)
  override def list: Unit = println("simulating only reading everything on a database")
}



object ClassAbstractModifierTest extends App {
  val db = Database
  db.save("my object")
  db.appendOnDatalake("my object")
  db.list
}


