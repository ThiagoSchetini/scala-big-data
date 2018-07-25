package com.scalabigdata.syntax



/**
  * Traits are abstract classes, like interfaces
  */
trait ClassAbstractModifier {
  def save
  def update
  def delete
}

trait BigData {
  def appendOnDatalake
}



object Database extends ClassAbstractModifier with BigData {
  override def save = println("saving")
  override def update = println("updating")
  override def delete= println("deleting")
  override def appendOnDatalake = println("putting on datalake")
}



object ClassAbstractModifierTest extends App {
  val db = Database
  db.save
  db.appendOnDatalake
}


