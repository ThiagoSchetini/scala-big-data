package com.scalabigdata.syntax


class X(val i: Int)
class Y(val i: Int)

// companion object of X
object X { implicit def xx: X = new X(1) }


class Method {
  def x(implicit x:X) = println(x.i)
  def y(implicit y:Y)= println(y.i)
}


trait M {

  // says that "M cannot be mixed into a concrete class that does not also extend Method"
  this: Method =>

  implicit def x1 = new X(10)
  implicit def y1 = new Y(100)

  def testy = y
  def testx = x

}


trait SM extends M {

  this: Method =>

  implicit def x2 = new X(20)
  implicit def y2 = new Y(200)

  def testy2 = y

}


object TestThis extends App {

  // implicit resolved from companion object of X
  new Method().x

  // explicit applied so that value is used
  new Method().x(new X(33))

  // will never use implicit inside M. implicit resolved from companion object of X
  (new Method with M).x

  // local scope overrides companion object implicit
  implicit def x = new X(30)
  new Method().x

  // explicit applied so that value is used
  new Method().x(new X(5))

  // local scope overrides companion object implicit
  (new Method with M).x



}