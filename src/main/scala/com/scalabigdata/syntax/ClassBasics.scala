package com.scalabigdata.syntax


/**
  * It's called a Complex Class because it has parameters
  *
  * Constructor: implicit defined by class parameters
  * Class Attributes: are the parameters
  * Getters/Setters: encapsulated by the methods real, imaginary, special
  */
class ComplexClass( // it is the basic constructor
                    real: Double,                   // private val (implicit)
                    imaginary: Double,              // private val (implicit)
                    var special: Double,            // public (implicit)
                    private var password: String,   // private variable
                    securityCode: Int = 579111      // default value
                  ) {

  /*
    real and imaginary are immutable (val), so they are protected anyway to avoid any changes
    the methods above are like getters and setters
   */
  def acessReal = real
  def acessImaginary = imaginary


  /*
    private password can only be acessed by this methods
   */
  def giveMyPass(securityCode: Int) = {
    if (securityCode == this.securityCode)
      password
    else
      throw new Exception("Incorrect security code")
  }

  def checkPass(password: String): Boolean = if (password == this.password) true else false

  def updatePass(oldPass: String, newPass: String): Boolean = {
    if(checkPass(oldPass)) {
      this.password = newPass
      true
    }
    else if (oldPass == newPass) throw new Exception("passwords are the same")
    else throw new Exception("actual password is wrong!")
  }


  // override is very simple
  override def toString: String =
    s"""
       |real $real
       |imaginary $imaginary
       |special $special
     """.stripMargin

}

object ComplexClassTest extends App {

  val obj = new ComplexClass(1.5, 2.3, 5.5, "admin")

  println("special value: " + obj.special)
  obj.special = 3.3
  println("updated special value: " + obj.special)

  println("using other values: " + (obj.acessImaginary + obj.acessReal))

  println("My password is: " + obj.giveMyPass(579111))
  println("Checking my password " + obj.checkPass("admin"))
  println(obj.updatePass("admin", "1234"))
  println("My password is: " + obj.giveMyPass(579111))

  // using the toString override
  println("\n\n\nto string override")
  println(obj.toString)

}
