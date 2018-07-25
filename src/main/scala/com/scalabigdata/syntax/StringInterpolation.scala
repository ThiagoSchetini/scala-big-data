package com.scalabigdata.syntax


class StringInterpolation {

  def strInterpolation(): String = {
    val title = "interpolated string"
    val a = Array(11, 9, 0)
    s"""
       |$title
       |expression: ${a(0)} + ${a(1)}
       |result: ${a(0) + a(1)}
     """.stripMargin
  }

  def strFormatting(): String = {
    val number = 1.99339d
    f"using formatted interpolation: $number%1.2f"
  }

}

object StringInterpolationTest extends App {

  val obj = new StringInterpolation

  println(obj.strInterpolation())
  println(obj.strFormatting())

}
