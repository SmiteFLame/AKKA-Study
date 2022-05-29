package com.scala.code.test

object ObjectOrientation extends App {
  //java equivalent: public static void main(String[] args])

  // class and instance
  class Animal {
    // define fields;
    val age: Int = 0

    // define methods
    def eat() = println("Im eating")
  }

  val anAnimal = new Animal

  // inheritance
  class Dog(val name: String) extends Animal // constructor defineition

  val aDog = new Dog("Lassie")

  // constructor arguments are NOT fields: need to put a val before constructor argument
  aDog.name

  val aDeclaredAnimal: Animal = new Dog("Hachi")
  aDeclaredAnimal.eat()

  // abstract class
  abstract class WalkingAnimal {
    val hasLegs = true // bt default public, can restrict by adding protected or private

    def walk(): Unit
  }

  // "interface" = ultimate abstract type
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait Philosopher {
    def ?!(thought: String): Unit // valid method name
  }

  // single-class interitance, multi-trait "mixing"
  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println("i am eating you, animal")

    override def ?!(thought: String): Unit = println(s"i was thinking: $thought")
  }

  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog // infix notation = object method argument, only available for methods with ONE argument
  aCroc ?! "What if we can fly?"

  val basicMath = 1 + 2
  val anotherBasicMath = 1.+(2)

  // anonymous Classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am dinosaur so I can eat pretty much anything")
  }

  // the only instance of the MySingleton type
  object MySingleton {
    val mySpeicalValue = 53278

    def mySpeicalMethod(): Int = 5327

    def apply(x: Int): Int = x + 1
  }

  MySingleton.mySpeicalMethod()
  MySingleton.apply(65)
  MySingleton(65) // equivalent to MySingletion.apply(65)

  object Animal { // companions - companion object
    // companions can access each other`s private fields/method
    // singleton Animal and instances of Animal are different things
    val canLiveIndefinitely = false
  }

  val animalsCanLiveForever = Animal.canLiveIndefinitely // "static" fields/methods

  /**
   * case Classes = lightWeight data structures with some boilderplate
   * - sensible equals and hash code
   * - serialization
   * - companion with apply
   * - pattern matching
   */
  case class Person(name: String, and: Int)

  // may be constructed without new
  val bob = Person("Bob", 54) // Person.apply("Bob", 54)

  // gnenerices
  abstract class MyList[T] {
    def head: T
    def tail: MyList[T]
  }

  // using a generic with a concrete type
  val aList: List[Int] = List(1, 2, 3) // List.apply(1,2,3)
  val first = aList.head //Int
  val rest = aList.tail
  val aStringList = List("hello", "Scala")
  val firstString = aStringList.head // string

  // Point #1: in Scala we usually operate with IMMUTABLE values/objects
  // Any modification to an object must return ANOTHER object
  /**
   * Benifits:
   * 1) works miracles in multithreaded/distributed env
   * 2) helps making sense of the code ("reasoning about")
   */
  val reversedList = aList.reverse // returns a NEW list

  // Point #2: Scala is cloest to the OO ideal
}
