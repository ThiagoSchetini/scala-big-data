package com.scalabigdata.patternmatching

case class Car(model:String, year:Int)


class Simple {

  def matchCar(c: Car): String = c match {
    case Car(model, 1980) => s"We found an old car: $model"
    case Car(model, 2018) => s"we got a new car: $model"
    case Car(model, 2000) => s"Here we have a milenium car: $model"
    case Car(model, year) => s"Any car: $model from year $year"
  }

}


object SimpleTest extends App {

  val simple = new Simple
  val mercedez = Car("Mercedez", 1980)
  val bmw = Car("BMW", 2018)
  val fiat = Car("FIAT", 2017)

  println(simple.matchCar(mercedez))
  println(simple.matchCar(bmw))
  println(simple.matchCar(fiat))

}
