package com.scalabigdata.syntax

import scala.beans.BeanProperty


/**
  * About getters and setters
  */
class SimpleClass {

  // generates for JVM: private field + public getter and setter
  var name:String = _

  // generates for JVM: private field + public getter (only cause it's immutable!)
  val identifier:Int = 1

  // generates for JVM: private field + private getter + private setter
  private var profession:String = _

  // manual generating for JVM: redefining public getter and setter
  private var privateNickname: String = _
  def nickname = privateNickname                                   // Getter
  def nickname_= (nickname: String) = privateNickname = nickname    // Setter: mutators, use “_=” appended

  // no getters and setters generated at all for JVM !!!
  private[this] var something:Int = 0

  // generates for JVM: normal getter and setter (_=) + getXxxx + setXxxx
  // Bean property makes the propertie compatible with the Java tools
  @BeanProperty var anotherName:String = _

  // generates for JVM: normal getter + getXxxx only because it's final
  @BeanProperty val someConst:Int = 5

}



class ClassChildAccess {
  private val number = 99

  // can access a private getter because this object is of same class of this
  def calculus(sameClass: ClassChildAccess): Int = sameClass.number + number
}



/**
  * The Primary Constructor
  * If there are no parameters after the class name, then the class has a primary constructor with no parameters
  * If a parameter without val or var is used inside at least one method, it becomes a field
  */
class ComplexClass(
                    real: Double,                       // not becomes a field (no get/set generated for JVM)
                    val imaginary: Double,
                    var special: Double,
                    private var password: String,
                    var securityCode: Int = 579111,
                    @BeanProperty var some:String,      // have getSome() and setSome() generated to JVM
                    @BeanProperty val other:String      // have only getOther generated to JVM (it's final)
                  ) {

  /*
    private password can only be accessed by this logical methods
   */
  def giveMyPass(securityCode: Int) = {
    if (securityCode == this.securityCode)
      password
    else
      throw new Exception("Incorrect security code")
  }

  def checkMyPass(password: String): Boolean = if (password == this.password) true else false

  def updateMyPass(oldPass: String, newPass: String): Boolean = {
    if(checkMyPass(oldPass)) {
      this.password = newPass
      true
    }
    else if (oldPass == newPass) throw new Exception("passwords are the same")
    else throw new Exception("actual password is wrong!")
  }


  // override toString is very simple
  override def toString: String =
    s"""
       |real $real
       |imaginary $imaginary
       |special $special
     """.stripMargin

}



object ClassBasicsTests extends App {

  val simple = new SimpleClass
  simple.name = "Thiago Schetini"
  println(simple.name)
  println(simple.identifier)
  //simple.profession >>> not accessible



  val child1 = new ClassChildAccess
  val child2 = new ClassChildAccess
  println(child1.calculus(child2))



  val complex = new ComplexClass(1.5, 2.3, 5.5, "admin", some = "blah")

  println("special value: " + complex.special)
  complex.special = 3.3

  println("updated special value: " + complex.special)

  println("using other values: " + (complex.getImaginary + complex.getReal))

  println("My password is: " + complex.giveMyPass(579111))
  println("Checking my password " + complex.checkMyPass("admin"))
  println(complex.updateMyPass("admin", "1234"))
  println("My password is: " + complex.giveMyPass(579111))

}
