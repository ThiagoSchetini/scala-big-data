package com.scalabigdata.syntax

import scala.collection.mutable


/**
  * "If the scope of the type parameter is small enough, a mnemonic can be used in place of a longer, descriptive name"
  * https://docs.scala-lang.org/style/naming-conventions.html
  */
trait Mnemonics[K, V] {

  def get(key: K): Option[V]
  def set(key: K, value: V): Unit

}



class MnemonicsImpl extends Mnemonics[Int, String] {

  private val hashMap = mutable.HashMap.empty[Int, String]

  override def get(key: Int): Option[String] = hashMap.get(key)

  override def set(key: Int, value: String): Unit = hashMap.+=(kv = (key, value))

  def allValues: List[String] = hashMap.toList.map(_._2)

}



class Other {

  def doSomething(m: MnemonicsImpl) = m.allValues

}



object OtherTest extends App {

  val other = new Other
  val mnemonics = new MnemonicsImpl
  mnemonics.set(1, "first")
  mnemonics.set(2, "second")
  mnemonics.set(3, "thirdy")

  val result = other.doSomething(mnemonics)
  println(result.foreach(println(_)))
}
