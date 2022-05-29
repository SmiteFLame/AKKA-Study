package com.scala.code.test

object Basic extends App {
  val meaningOfLife: Int = 42 // const int meaningOfLife = 42;
  val aBoolean = false // Type is Optional
  val string1 = "TEST $meaningOfLife"
  val string2 = s"TEST $meaningOfLife"

  val ifExpression = if (meaningOfLife > 43) 56 else 999
  val chainedIfExpression = {
    if (meaningOfLife > 43) 56
    else if (meaningOfLife < 0) -2
    else if (meaningOfLife > 999) 78
    else 0
  }

  /* Code Blocks */
  val aCodeBlock = {
    // definitions
    val aLocalValue = 67

    // value of block is the value of the last expression
    aLocalValue + 3
  }
  println(s"aCodeBlock: $aCodeBlock")

  // define a function
  def myFunction(x: Int, y: String): String = y + " " + x

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else n * factorial(n - 1)


  // In Scala we dont use loops or iteration, we use RECURSION!

  // the Unit Type = no meaingful value === "void" in other languages
  // type of SIDE EFFECTS
  println("I love Scala")
}
