package com.scalabigdata.syntax

class ClassAuxiliaryConstructor(private var obrigatoryNumber:Double) {

  var anotherNumber:Int = 12
  var someString:String = "anything"
  var someChar:Char = 'a'

  def this(obrigatoryNumber:Double, anotherNumber: Int) {
    this(obrigatoryNumber)
    this.anotherNumber = anotherNumber
  }

  def this(obrigatoryNumber:Double, anotherNumber: Int, someString: String) {
    this(obrigatoryNumber, anotherNumber)
    this.someString = someString
  }

  def this(obrigatoryNumber:Double, anotherNumber: Int, someString: String, someChar: Char) {
    this(obrigatoryNumber, anotherNumber, someString)
    this.someChar = someChar
  }

  override def toString: String =
  s"""
    |obrigatory = $obrigatoryNumber
    |another = $anotherNumber
    |string = $someString
    |char = $someChar
  """.stripMargin
}

object ClassAuxiliaryConstructorTest extends App {

  var obj = new ClassAuxiliaryConstructor(3.99)
  println(obj)

  var obj2 = new ClassAuxiliaryConstructor(4.99, 8, "nothing", 'A')
  println(obj2)

}
