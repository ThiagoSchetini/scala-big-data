package com.scalabigdata.syntax


class B {
  def x(implicit x:X)= println(x.i)
  def y(implicit y:Y)= println(y.i)
}


/**
  * says that "A cannot be mixed into a concrete class that does not also extend B"
  */
trait A { this: B => }


/**
  * says that "any (concrete or abstract) class mixing in A will also be mixing in B"
  */
trait Z extends B


class One extends Z { /* ok */ }

class Two extends B with A { /* needs to extend B to mix A */ }