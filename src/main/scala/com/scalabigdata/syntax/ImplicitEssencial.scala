package com.scalabigdata.syntax


class ImplicitEssencial {

  /**
    * implicit variable won't work here
    */
  def method(str: String)(implicit imp: String) = s"argument value: $str, implicit value: $imp"
}


object ImplicitEssencialTest extends App {

  /**
    * need to create an string implicit val on this scope to use on the class method
    */
  val implicits = new ImplicitEssencial
  implicit val implicitString: String = "implicit val"
  val result3 = implicits.method("arg val")
  println(result3)

  /**
    * dont using implicit value (overriding it)
    */
  println(implicits.method("arg val")("arg val"))

}