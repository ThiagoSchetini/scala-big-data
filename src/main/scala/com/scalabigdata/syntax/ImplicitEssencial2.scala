package com.scalabigdata.syntax


trait Monoid[A] {
  def add(x: A, y: A): A
  def unit: A
}


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


class MyImplicit {

  /**
    * recursive sum() call
    */
  def sum[A](a: List[A])(implicit m: Monoid[A]): A = if (a.isEmpty) m.unit else m.add(a.head, sum(a.tail))
}


object MyImplicitTest extends App {

  /**
    * implicit values necessary here on this scope
    */
  implicit val intMonoid: Monoid[Int] = new IntMonoid
  implicit val stringMonoid: Monoid[String] = new StringMonoid

  val imp = new MyImplicit
  println(imp.sum(List(1,3,5,7)))
  println(imp.sum(List[Int]()))
  println(imp.sum(List("zeta", "beta", "alpha")))

  /**
    * with no implicit value
    */
  println(imp.sum(List(1.10, 3.30, 5.50, 7.70))(new DoubleMonoid))
}