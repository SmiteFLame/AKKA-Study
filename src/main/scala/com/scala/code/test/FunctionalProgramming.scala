package com.scala.code.test

object FunctionalProgramming extends App {
  // Scala is OO
  class Person(name: String) {
    def apply(age: Int) = println(s"I have aged $age years")
  }

  val bob = new Person("Bob")
  bob.apply(43)
  bob(43) // INVOKING bob as a function === bob.apply(43)

  /**
   * Scala runs on the JVM
   * Functional Programming:
   * - compose functions
   * - pass functions as args
   * - return functions as results
   *
   * Conclusion: FunctionX = Function1, Function2, ... Function 22
   */

  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg + 1
  }

  simpleIncrementer.apply(23) // 24
  simpleIncrementer(23)

  // defined a function

  // ALL SCALA FUNCTIONS ARE INSTANCES OF THESE FUNCTION_X TYPES

  // function with 2 arguments and a String return type
  val stringConcatenator = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  stringConcatenator("I Love ", "Scala")

  val doubler: Function1[Int, Int] = (x: Int) => 2 * x
  val doubler2: Int => Int = (x: Int) => 2 * x
  doubler(4) // 8

  // higher-order functions: tkae functions as args/return functions as results
  val aMappedList = List(1, 2, 3).map(x => x + 1) // Higher order functions
  println(aMappedList) // 각각의 값을 하나씩 변하게 해준다.

  val aFlatMappedList = List(1, 2, 3).flatMap(x => List(x, 2 * x))
  // 작은 리스트들을 큰 리스트 틀로 바꿔주고 합치게 해준다.
  println(aFlatMappedList)

  val aFilteredList = List(1, 2, 3, 4, 5).filter(_ <= 3) // 뒤에 것을 기준
  println(aFilteredList)

  val allPairs = List(1, 2, 3).flatMap(number => List('a', 'b', 'c').map(letter => s"$number-$letter"))
  println(allPairs) // List(1-a, 1-b, 1-c, 2-a, 2-b, 2-c, 3-a, 3-b, 3-c)

  // for comprehensions
  val alternativePairs = for {
    number <- List(1, 2, 3)
    letter <- List('a', 'b', 'c')
  } yield s"$number-$letter"
  // equivalent to the map/flatMpa chain above

  val aList = List(1, 2, 3, 4, 5)
  val firstElement = aList.head
  val rest = aList.tail
  val aPrependList = 0 :: aList // List(0,1,2,3,4,5)
  val anExtendedList = 0 +: aList :+ 6 // List(0,1,2,3,4,5,6)

  // sequences
  val aSequence: Seq[Int] = Seq(1, 2, 3) // Seq.apply(1,2,3)
  val accessedElement = aSequence.apply(1) //the element at index 1: 2

  // vectors: fast Seq implementations
  val aVector = Vector(1, 2, 3, 4, 5)

  val aSet = Set(1, 2, 3, 4, 1, 2, 3) // Set(1,2,3,4)
  val setHas5 = aSet.contains(5) //  false
  val anAddedSet = aSet + 5 // Set(1,2,3,4,5)
  val aRemoveSet = aSet - 3 // Set(1,2,4)

  // ranges
  val aRange = 1 to 1000
  val twoByTwo = aRange.map(x => 2 * x).toList // List(2,4,.. 2000)
  val bRange = 1 until 1000

  // tuples = groups of vales under the same values
  val aTuple = ("Bon Jovi", "Rock", 1972)

  // maps
  val aPhoneNumber: Map[String, Int] = Map(
    ("A", 1)
  )
}
