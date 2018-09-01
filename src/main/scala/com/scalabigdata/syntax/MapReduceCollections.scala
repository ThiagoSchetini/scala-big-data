package com.scalabigdata.syntax

import scala.collection.mutable

class FunctionalScala {

  val ifElseExample: (Int, Int) => Int = (x, y) => if (x == 5) x else x + y

  /**
    * addFive A and B are the same thing
    */
  val addFiveA: Int => Int = _ + 5
  val addFiveB: Int => Int = x => x + 5

  val sumAll: (Int, Int, Int) => Int = _ + _ + _

  /**
    * findMax way A and B are the same thing
    */
  val findMaxFirstWay: (Int, Int) => Int =  _ max _

  val findMaxSecondWay = (x: Int, y: Int) => {
    val winner = x max y
    println(s"compared $x to $y, $winner was larger")
    winner
  }

}


object UsingMapReduce extends App {

  /**
    * creating some basic Collections
    */
  val numbers: List[Int] = List(1, 1, 2, 3, 5, 7, 10, 23, 33, 53)
  val fiveMultiples: List[Int] = List(5, 15, 25)
  val people: Vector[String] = Vector("al", "hannah", "emily", "christina", "aleka")
  val fruits = List("apple", "apple", "orange", "apple", "mango", "orange", "apple", "apple", "apple", "apple")


  // some pre created functions
  val functions = new FunctionalScala
  val listFive = numbers map functions.addFiveA // .map(_ + 5)
  println(listFive)


  // reducing
  val sum = numbers.reduce{(a, b) => println(s"$a + $b = ${a + b}"); a + b}
  println(s"final sum: $sum")


  // reduce left/right !same result = 53
  val resultA = numbers.reduceLeft(functions.findMaxFirstWay)
  println(s"resultA: $resultA")
  val resultB = numbers.reduceRight(functions.findMaxSecondWay)
  println(s"resultB: $resultB")

  val longestName = people.reduceLeft((a, b) => if (a.length > b.length) a else b)
  println(longestName)
  val shortestName = people.reduceRight((a, b) => if (a.length < b.length) a else b)
  println(shortestName)


  // fold left/right == merges the parameter value as it was the first one
  val leftFolded = fiveMultiples.foldLeft(5)(_ * _)
  println(s"leftFolded: $leftFolded")
  val rightFolded = fiveMultiples.foldRight(5)(_ * _)
  println(s"rightFolded: $rightFolded")
  val leftReduced = fiveMultiples.reduceRight(_ * _) // different of fold ... could be .product
  println(s"leftReduced: $leftReduced")

  /**
    * scan left/right returns a Collection with the values applied on all elements
    * it's like cumulative
    * right starts with all alements and eliminates one of left on each iteration
    */
  val scannedLeft = numbers.scanLeft(10)(_ * _)
  println(scannedLeft)
  val normalScan = numbers.scan(10)(_ * _)
  println(normalScan)
  val scannedRight = numbers.scanRight(10)(_ * _)
  println("!!! right starts with all alements and eliminates one of left on each iteration")
  numbers.scanRight(10)((a, b) => {println(s"$a * $b = ${a * b}"); a * b})
  println(scannedRight)



  /**
    * The order in which operations are performed on elements is unspecified and may be nondeterministic
    * .par = parallel
    */
  numbers.par.reduce(functions.findMaxSecondWay)


  // concatenating some methods
  val calculated = numbers.filter(_ < 20).reduce(_ / 2 + _)
  println(calculated)
  val changeSyntax = numbers filter (_ > 20) reduce (_ / 2 * _)
  println(changeSyntax)


  /**
    * more parallelism (.par)
    * a deterministic execution would result in -500498, but on parallel execution value of x will never be the same ...
    */
  val x = (1 to 1000).toList.par.reduce(_ - _)
  println(x)


  /**
    * more parallelism (.par)
    * Although the execution is "out of order", the result will be reassembled in order, see:
    */
  val alphabet = List("abc","def","ghi","jk","lmnop","qrs","tuv","wx","yz").par.reduce(_ ++ _)
  println(alphabet)


  /**
    * yield use
    * on each iteration the result is saved inside a Collection and this collection is returned on the end
    * "List Comprehension"
    */
  val multiplicationVector = for (i <- 1 to 5) yield i * 2
  println(multiplicationVector)

  val modulesVector = for (i <- 1 to 99) yield i % 2
  println(modulesVector)

  def apples = for {
      name <- fruits
      if name.endsWith("ple")
    } yield name
  println(apples)


  // the classic word count
  val counted = fruits.map((_, 1)).groupBy(_._1).mapValues(_.length)
  println(counted.toList)

}
