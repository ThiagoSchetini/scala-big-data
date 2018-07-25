package com.scalabigdata.syntax

/*
 * trait is like an interface on JAVA
 * [] is the same as generics on JAVA
 */
trait TraitIterator[A] {
  def hasNext: Boolean
  def next(): A
}

class IntIterator(len: Int) extends TraitIterator[Int] {
  private var current = 0

  override def hasNext: Boolean = current < len

  override def next(): Int = {
    if (hasNext) {
      val x = current
      current += 1
      x
    } else 0
  }

}

object IteratorTest extends App {

  val iterator = new IntIterator(7)
  println(iterator.next())
  println(iterator.next())
  println(iterator.next())
  println(iterator.next())
  println(iterator.next())
  println(iterator.next())

}
