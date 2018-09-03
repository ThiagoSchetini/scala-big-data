package com.scalabigdata.syntax


class X(val i: Int)
class Y(val i: Int)
object X { implicit def xx: X = new X(1) }


class Method {
  def x(implicit x:X) = println(x.i)
  def y(implicit y:Y)= println(y.i)
}


trait M {

  // says that "M cannot be mixed into a concrete class that does not also extend Method"
  this: Method =>



}

class ImplicitAdvanced extends Method with M {



}
