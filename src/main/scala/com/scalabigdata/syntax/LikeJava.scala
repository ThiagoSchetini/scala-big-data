package com.scalabigdata.syntax




/**
  * In Scala the convention is to use an immutable reference to a property
  * It's gonna have its own getter and setter
  */
class ScalaConvention {


  val name: Property[String] = Property("Thiago Schetini")

}

/**
  * It's not the convention of Scala !!!
  * Java getter/setter paradigm was often used to work around a lack of first class support for Properties and bindings
  */
class LikeJava {

  private var _name: String = _

  // Getter
  def name = _name

  // Setter
  //For mutators, the name of the method should be the name of the property with “_=” appended
  def name_= (name: String) = _name = name

}
