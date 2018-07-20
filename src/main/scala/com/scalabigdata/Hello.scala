package com.scalabigdata

object Hello extends App {

  println(" ============== RUNNING ======================")

  /*
   * STRING SPECIALS
   */

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


  /*
   * SCALA FUNCTIONS
   */

  def simpleFunction(x: Int, y: Int): Int = {           // (var: Type): ReturnType = { ... execution block ... }
    val x2 = x * x
    val y2 = y * y
    x2 + y2                                             // do not use return! >>> not good practice!
  }

  def functionWithDefaultValue(x:Int, y:Double = 88.5): Double = {
    if (x == 5)
      x                                                 // do not use return! >>> not good practice!
    else
      x + y                                             // do not use return! >>> not good practice!
  }

  def functionInOneLine(x:Int, y:Double = 88): Double = if (x == 5) x else x + y


  /*
   * THE FUNCTIONAL WAY OF SCALA !
   * obs: it's used to be anonymous
   */
  def funcFunctional: (Int, Int) => Int = (x, y) => if (x == 5) x else x + y
                                                        // it's called anonymous
                                                        // can be assigned with def, val, var ...

  def anonymous: Int => Int = x => x + 1                // anonymous function: Type => ReturnType = ... execution

  def weirdUnderline: Int => Int = _ + 1                // " _ " for a parameter used only one time
                                                        // (... x => x...) turn on _

  def moreUnderline: (Int, Int, Double) => Double = _ + _ + _
                                                        // first, second and third parameters represented as _

  def anonymousWithBlock: (Int, Int) => Int = { (x, y) =>
    if (x == 5)
      x
    else
      x + y
  }


  /*
   * CALLBACKS USING ANONYMOUS/FUNCTIONAL
   * Unit is the same as void on Java
   */
  def noReturnFunction(callback: () => Unit): Unit = callback()
                                                        // executing a function received as parameter

  noReturnFunction(() => println("i'm a anonymous function been executed >>> " + (7 + 7)))
                                                        // an anonymous function is a parameter of someFunction

  def anotherNoReturnFunction(optionalCallback: Option[() => Unit] = None): Unit = {
    optionalCallback.map(c => c())
  }

  anotherNoReturnFunction(Some(() =>
    println("anonymous been executed >>> " + (5 + 5))
  ))

  def callbackHasParam(callbackWithParam: Double => Double): String = {
    val internalNumber:Double = 9.99
    val internalResult:Double = callbackWithParam(internalNumber)
    "returning the result >>> " + internalResult
  }
  println(callbackHasParam((x: Double) => x + x))

  def callbackHasParams(callbackWithParams: (Double, Int) => Double): String = {
    val internalDouble: Double = 3.33
    val internalInt: Int = 10
    val internalResult:Double = callbackWithParams(internalDouble, internalInt)
    "returning the result >>> " + internalResult
  }
  println(callbackHasParams((x: Double, y:Int) => x + y))

  def execute(callback: (Any, Any) => Any, x: Any, y: Any): Any = callback(x, y)
  case class Person(name: String)                         // defining a class
  val thiago = Person("Thiago Schetini")

  val result = execute(
    (a: Any, b: Any) => println("printing objects >>> " + a + ", " + b),
    5543.23,
    thiago.name
  )


  /*
   * CALLBACKS + LOOP
   */
  def hello = () => println("hello world")

  def sayHello(callback: () => Unit, times: Int) = for( i <- 1 to times ) callback()

  sayHello(hello, 5)


}
