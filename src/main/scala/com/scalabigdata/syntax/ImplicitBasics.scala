package com.scalabigdata.syntax


class X(val i: Int)
class Y(val i: Int)

// companion object of X (an object with the same name of the class)
object X { implicit def xx: X = new X(1) }


class Method {
  def x(implicit x:X) = println(x.i)
  def y(implicit y:Y)= println(y.i)
}


trait M {

  // says that "M cannot be mixed into a concrete class that does not also extend Method"
  this: Method =>

  implicit def x1 = new X(11)
  implicit def y1 = new Y(111)

  def testy = y
  def testx = x

}


trait SM extends M {

  this: Method =>

  implicit def x2 = new X(22)
  implicit def y2 = new Y(222)

  def testy2 = y
  def testx2 = x

}


object TestThis extends App {

  // implicit resolved from companion object of X = 1
  new Method().x

  // explicit applied so that value is used = 33
  new Method().x(new X(33))

  // will never use implicit inside M. implicit resolved from companion object of X = 1
  (new Method with M).x

  // local scope overrides companion object implicit = 30 and 30 again
  implicit def x = new X(30)
  new Method().x
  (new Method with M).x

  // again, explicit applied so that value is used = 5
  new Method().x(new X(5))


  /**
    * now, using the scope of the traits
    */

  // testx is defined within M so the implicits within M overrides the companion object implicit = 11
  (new Method with M).testx

  // testx is within M (not SM) so the implicit within M is used = 11
  (new Method with SM).testx

  // testx2 is within SM so the implicit within SM overrides the implicit in M and the companion object = 22
  (new Method with SM).testx2

}