package com.scalabigdata.syntax


/**
  * the interface

trait Monoid[A] {
  def add(x: A, y: A): A
  def unit: A
}


/**
  * implementations for Int, String and Double
  */
class IntMonoid extends Monoid[Int] {
  override def add(x: Int, y: Int) = x + y

  override def unit: Int = 0
}

class StringMonoid extends Monoid[String] {
  override def add(x: String, y: String): String = x.concat(y)

  override def unit: String = ""
}

class DoubleMonoid extends Monoid[Double] {
  override def add(x: Double, y: Double): Double = x + y

  override def unit: Double = 0
}


/**
  * Companion Objects for the implementations
  */
object IntMonoid {implicit def impl = new IntMonoid}
object StringMonoid {implicit def impl = new StringMonoid}
object DoubleMonoid {implicit def impl = new DoubleMonoid}


/**
  * implicit contains the objects
  */
class Monoids {
  def x(implicit obj: IntMonoid) = obj
  def xx(implicit obj: StringMonoid) = obj
  def xxx(implicit obj: DoubleMonoid) = obj
}


trait Everyone {
  this: Monoids =>
}


/**
  * using generics and a recursive sum() call. Using a implicit implementation (one of the objects)
  */
class MyImplicit {
  def sum[A](a: List[A])(implicit m: Monoid[A]): A = if (a.isEmpty) m.unit else m.add(a.head, sum(a.tail))
}


object MyImplicitTest extends App {

  /**
    * implicit values necessary here on this scope
    */
  //implicit val intMonoid: Monoid[Int] = new IntMonoid
  //implicit val stringMonoid: Monoid[String] = new StringMonoid
  val monoids = new Monoids



  val imp = new MyImplicit


  println(imp.sum(List(1,3,5,7)))
  println(imp.sum(List[Int]()))
  println(imp.sum(List("zeta", "beta", "alpha")))

  /**
    * with explicit implementation
    */
  println(imp.sum(List(1.10, 3.30, 5.50, 7.70))(new DoubleMonoid))

}
  */