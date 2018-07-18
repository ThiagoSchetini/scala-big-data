package com.scalabigdata.basics

/*
 * It's called a Complex Class because it has parameters
 */
class ComplexClass(real: Double, imaginary: Double, special: Double) {

  // the "return" after "=" symbol are always ommited in Scala
  def re() = real
  def im() = imaginary

  // methods without arguments in Scala don’t have parenthesis
  def ss = special

  // override is like JAVA
  override def toString: String =
    s"""
       |real ${re()}
       |imaginary ${im()}
       |special ${ss}
     """.stripMargin
}

object TestComplex {

  def main(args: Array[String]): Unit = {

    val myObject = new ComplexClass(1.5, 2.3, 5.5)

    // accessing methods that have zero arguments
    println(myObject.im() + myObject.re())

    // accessing a method that don't have arguments
    println(myObject.ss)

    // using the toString rewrited
    println(myObject.toString)

  }

}
