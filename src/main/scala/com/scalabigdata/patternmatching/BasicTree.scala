package com.scalabigdata.patternmatching


abstract class Tree
case class Sum(l: Tree, r: Tree) extends Tree
case class Var(n: String) extends Tree
case class Const(v: Int) extends Tree


trait PatternMatching {

  type Environment = String => Int

  def evaluation(t: Tree, env: Environment): Int = t match {
    case Sum(l, r)  => evaluation(l, env) + evaluation(r, env)
    case Var(n)     => env(n)
    case Const(v)   => v
  }

  def derive(t: Tree, v: String): Tree = t match {
    case Sum(l, r)  => Sum(derive(l, v), derive(r, v))
    case Var(n) if v == n => Const(1)
    case _ => Const(0)
  }

}


object Execute extends App with PatternMatching {
  val env: Environment = {case "x" => 5 case "y" => 7}
  val exp: Tree = Sum(Sum(Var("x"), Var("x")), Sum(Const(7), Var("y")))

  println(evaluation(exp, env))
  println(derive(exp, "x"))
  println(derive(exp, "y"))
}