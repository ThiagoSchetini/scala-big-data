package com.scalabigdata.syntax


class SomeClassUsesImplicit {
  def methodUseImplicit(str: String)(implicit imp: String) = s"argument value: $str, implicit value: $imp"
}


object ImplicitTest extends App {

  implicit val myImplicitInt: Int = 100

  def someMethod(message: String)(implicit anyname: Int) = s"$message and the number is: $anyname"

  val result1 = someMethod("using implicit")
  println(result1)

  val result2 = someMethod("using a value")(400)
  println(result2)


}


