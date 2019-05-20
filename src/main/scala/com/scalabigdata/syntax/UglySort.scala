package com.scalabigdata.syntax

import scala.collection.mutable.ArrayBuffer

object UglySort {

  def sort(vector: ArrayBuffer[Int]) = {
    var aux = 0
    for(i <- 0 until vector.length - 1) {
      for(k <- i to 0 by -1) {
        if (vector(k + 1) < vector(k)) {
          aux = vector(k)
          vector(k) = vector(k + 1)
          vector(k + 1) = aux
        }
      }
    }
    vector
  }
}

object Play extends App {
  var v = ArrayBuffer(3, 5, 2, 4, 6, 1, 7, 9, 1, 2, 3)

  val result = UglySort.sort(v)
  println(result)
}
