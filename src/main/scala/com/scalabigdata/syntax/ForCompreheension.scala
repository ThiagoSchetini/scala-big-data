package com.scalabigdata.syntax


object ForComprehension extends App {

  /**
    * we can use () or {} !!!
    */
  val aListOfNumbers = List(1, 2, 3, 4, 10, 20, 100)

  val x = for { n <- aListOfNumbers if n < 10 } yield math.pow(n, 2)
  println(x)


  case class User(name: String, age: Int)

  val userBase = List(
    User("Travis", 28),
    User("Kelly", 33),
    User("Jennifer", 44),
    User("Dennis", 23))

  val twentySomethings = for (user <- userBase if user.age >= 20 && user.age < 30) yield user.name
  println(twentySomethings)


  /**
    * On the first iteration, i == 0 and j == 0 so i + j != v and therefore nothing is yielded.
    * j gets incremented 9 more times before i gets incremented to 1
    */
  def foo(n: Int, v: Int) = for (i <- 0 until n; j <- i until n if i + j == v) yield (i, j)
  println(foo(10,10))

}
