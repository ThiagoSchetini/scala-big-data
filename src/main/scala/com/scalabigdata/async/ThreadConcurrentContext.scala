package com.scalabigdata.async


import scala.concurrent.ExecutionContext.Implicits.global
import org.apache.spark._
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

object ThreadConcurrentContext {

  def executeAsync[T](f: => T): Future[T] = {
    Future(f)
  }

  def awaitAll[T](it: Iterator[Future[T]], timeout: Duration): Iterator[T] = {
    Await.result(Future.sequence(it), timeout)
  }

}

object ConcurrentExecution extends App {

  /** Waits one second then returns the input. Represents some kind of IO operation **/
  def slowFoo[T](x: T):T = {
    println(s"slowFoo start ($x)")
    Thread.sleep(1000)
    println(s"slowFoo end($x)")
    x
  }

  val conf = new SparkConf().setAppName("testing").setMaster("local[*]")
  val sc = new SparkContext(conf)
  val nums = sc.parallelize(1 to 50)

  nums
    .map(x => ThreadConcurrentContext.executeAsync(slowFoo(x)))
    .mapPartitions(it => ThreadConcurrentContext.awaitAll(it, Duration.Inf))
    .foreach(x => println(s"finishing with $x"))
}
