package com.scalabigdata.reflections

import scala.collection.mutable
import scala.reflect.runtime.universe._
import scala.util.control.Breaks._

object Executor extends App {

  /* test 1 */
  val name = "LeBlanc"
  val props = Map(("skin", "white"), ("male", "false"), ("age", "23"), ("money", "66.99"), ("nicks", "alpha,beta,gama"))
  val obj = ReflectMyselfProps(name, props)
  val result1 = obj.validateMe
  println(s"test1 validation result: $result1")

  /* test 2 using try/catch to creation */
  try {
    val name2 = "Newton"
    val props2 = Map(("skin", "white"), ("male", ""), ("age", "23"), ("money", "66.99"), ("nicks", "xxx"))
    ReflectMyselfProps(name2, props2)
  } catch {
    case e: IllegalArgumentException => println(s"test2 validation result: ${e.getMessage}")
    case e: NumberFormatException => println(s"test2 validation result: ${e.getMessage}")
    case e: Exception => println(s"test2 validation result: ${e.getMessage}")
  }

  /* test 3 using try/catch and get validation of null */
  val name3 = "Pluto"
  val props3 = Map(("skin", null), ("male", "true"), ("age", "23"), ("money", "66.99"), ("nicks", "x"))
  val obj3 = ReflectMyselfProps(name3, props3)
  val result3 = obj3.validateMe
  println(s"test3 validation result: $result3")

  /* test 4 using try/catch and get validation of empty */
  val name4 = "Pluto"
  val props4 = Map(("skin", ""), ("male", "true"), ("age", "23"), ("money", "66.99"), ("nicks", "alpha,beta,gama"))
  val obj4 = ReflectMyselfProps(name4, props4)
  val result4 = obj4.validateMe
  println(s"test4 validation result: $result4")

  /* test 5 using try/catch and get validation of empty List */
  val name5 = "Pluto"
  val props5 = Map(("skin", "yellow"), ("male", "true"), ("age", "23"), ("money", "66.99"), ("nicks", ""))
  val obj5 = ReflectMyselfProps(name5, props5)
  val result5 = obj5.validateMe
  println(s"test5 validation result: $result5")


  /* serialize test */
  println(s"\nSerializedCSV:")
  println(s"${obj.serializeToCSV}")

}

trait ReflectMyself {
  val name: String

  def validateMe: Boolean = {
    val rm = scala.reflect.runtime.currentMirror

    val accessors = rm.classSymbol(this.getClass).toType.members.collect {
      case m: MethodSymbol if m.isGetter && m.isPublic => m
    }

    val instanceMirror = rm.reflect(this)
    var flag = true

    breakable {
      for (a <- accessors) {
        val value = instanceMirror.reflectMethod(a).apply()

        value match {
          case v: List[Any] =>
            if (v.nonEmpty)
              v.foreach(i => {
                if (i.asInstanceOf[String].isEmpty) {
                  flag = false
                  break
                }
              })

          case _ =>
            if (value == null || value.toString.isEmpty) {
              flag = false
              break
            }
        }
      }
    }

    flag
  }

  private def serializeList(list: List[String]): String = {
    val builder = new StringBuilder()
    val last = list.last
    val noLast = list.reverse.tail.reverse
    noLast.foreach(i => builder.append(s"$i,"))
    builder.append(last)
    builder.mkString
  }

  def serializeToCSV: StringBuilder = {
    val rm = scala.reflect.runtime.currentMirror
    val instanceMirror = rm.reflect(this)
    val accessors = rm.classSymbol(this.getClass).toType.members.collect {
      case m: MethodSymbol if m.isGetter && m.isPublic => m
    }
    val orderedFields = this.getClass.getDeclaredFields.toList.map(_.getName)
    val unorderedFields = new mutable.HashMap[String, String]()

    /* get unordered values */
    for (a <- accessors) {
      val value = instanceMirror.reflectMethod(a).apply()

      value match {
        case v: List[Any] => unorderedFields += a.name.decodedName.toString -> serializeList(v.asInstanceOf[List[String]])
        case v: Any => unorderedFields += a.name.decodedName.toString -> v.toString
      }
    }

    /* using original class sequence order */
    val fields = orderedFields zip orderedFields.map(unorderedFields)

    /* delimited out with only values */
    val csv = new StringBuilder()
    val delimiter = "|"
    fields.foreach(f => csv.append(s"${f._2}$delimiter"))
    csv.dropRight(1)
  }
}

object ReflectMyselfProps {
  def apply(name: String, props: Map[String, String]): ReflectMyselfProps = {
    new ReflectMyselfProps(
      name: String, props("skin"),
      props("male").toBoolean,
      props("age").toInt,
      props("money").toDouble,
      props("nicks").split(",").toList)
  }
}

case class ReflectMyselfProps(name: String,
                              skin: String,
                              male: Boolean,
                              age: Integer,
                              money: Double,
                              nicks: List[String]) extends ReflectMyself

