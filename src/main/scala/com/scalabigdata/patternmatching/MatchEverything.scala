package com.scalabigdata.patternmatching


case class Bus(model:String, year:Int)

class MatchEverything {

  def matchEverything(obj: Any): String = obj match {

    case "Helo World" => "Got a String Hello World"
    case obj: Double => s"Got a Double: $obj"
    case obj: Int if obj > 2000 => s"Got a number higher than 2000! -> $obj"
    case Bus(model, year) => s"Any bus: $model from year $year"
    case List(a, b, c) => s"The List is: $a, $b, $c"
    case _ => "Got Unknow Object"
  }

}


object MatchEverythingTest extends App {

  val obj = new MatchEverything
  println(obj.matchEverything(22.3))
  println(obj.matchEverything(33335))
  println(obj.matchEverything(List(4,5,6)))
  println(obj.matchEverything("Anything"))

  val bus = Bus("Mercedez", 1999)
  println(obj.matchEverything(bus))
}