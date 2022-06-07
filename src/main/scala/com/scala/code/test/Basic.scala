package com.scala.code.test

import scala.annotation.tailrec

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


  // method: 행위
  // function: Function2[T, T2, T3] -> Instance

  def test(a: Int)(b: Int): Int = ???

  def test2(a: Int): Int = ???

  /* 여기는 Heap에 생기는 것이다. */
  val fnInstance2: Int => Int = test(2)
  val fnInstance: Int => Int = test2
  //  val fnInstance1: Unit => Int = test2(1)

  // new String 이라면 새로운 인스턴스에 올리게 된다

  /**
   * 참조 투명성: 국소적 추론으로 코드 이해 가능
   * 이 값들은 immuatble을 기본으로 한다.
   * 변경을 하더라도 새로운 것을 만들어서 보내준다.
   * 그래서 이 블록 안에서만 신경을 쓰면 된다.
   */
  val a = "ILIKE".reverse

  /**
   * 참조 투명하지 않는 예
   * StringBuilder
   *
   * 계속 증가 하게 된다.
   * println도 마찬가지로 투명하지 않다.
   */

  val x = new StringBuilder("Hello")
  val y = x.append(" World")
  println(y)
  val z = x.append(" World")
  println(z)


  val stringA: String = "a"
  val valueX: Int = 1

  def testMethods: String = "aaa"

  def testMethods2: Unit = println("a")
  // AnyVal (Java에서 Object) 을 상속한 Unit
  // 모든지 무조건 반환을 한다. Unit

  val fn: Int => Int = ???
  val fn_1: Int => Int => Int = ???
  val fn_1_2 = (a: Int) => (b: Int) => a + b
  val fn_3: Int => Int = fn_1_2(2)

  /**
   * forLoop를 거의 쓰지 않는다.
   * flatMap의 다른 이름이 For
   */


}


object Functional extends App {
  def factorial(n: Int): Int = {
    /**
     * 리커시브하게 하면 계속 쌓이면서 계속 쌓인다.
     * 이 코드를 짜면 stack에 쌓이지 않는다
     * 무조건 마지막에 자신을 호출 하는 것이 있어야 된다.
     */
    @tailrec
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n - 1, n * acc)

    go(0, 1)
  }
}

object FunctionalExample extends App {
  // 익명함수가 있다.
  (x: Int) => x + 1
  val fn1: Int => Int => Int = (a: Int) => (target: Int) => a + target + 1

  val noArgument = () => 47
  val noArgument2: () => Int = () => 47

  /**
   * public 으로 사용하는 것에 대해서는 써 주는 것이 코드 가독성이 더 좋다.
   */
  println(noArgument) // 그냥 함수에 Function code 값이 나오게 된다.
  println(noArgument()) // 이 때 함수의 실행 값이 나오게 된다.

  def highOrderFnM(fn: Int => Int, targetValue: Int): Int = fn(targetValue)

  val highOrderFn: (Int => Int, Int) => Int = (fn: Int => Int, targetValue: Int) => fn(targetValue)

  def methodV1(value1: Int, value2: Int): Int = value1 + value2
  //  val methodV2 = methodV1(2, _) // 결과로 새로운 함수가 나오게 된다.
  //  println(methodV2(3))

  // 고차함수: 함수 그 자체를 넣을 수 있다.
  // 알고리즘을 다르게 넣을 수도 있다.

  def plusOne: Int => Int = value => value + 1

  def product: Int => Int = value => value * 2

  val resultFn: Int => Int = plusOne.compose(product)
  println(resultFn(5)) // resultFn.apply(5)

  // Spider 자바스럽게 사용하지만
  // 이러한 기능이 필요할 때에는 사용을 하면 된다.

  // 하나의 클래스에서 여러개의

  /**
   * trait = interface
   * trait에서 구현체를 넣을 수 있다.
   *
   * object = static 과 비슷, Singleton과 비슷 하다, 스프링 Bean 같은 느낌이다.
   *  - 유일하게 하나만 존재 해야되는 것이다.
   *    class = class 그냥 같은 것이다.
   *
   * class Class extends testClass
   * class Class extends testClass with test2Class
   */

  trait a

  trait b

  class c extends a with b

  class e extends c

  class Dog1(name: String)

  class Dog2(val name: String) // val을 만들면 Getter 만든다.

  // var를 추천하지 않는다.
  class Dog3(var name: String) // var을 만들면 Getter, Setter를 만든다.

  val dog1 = new Dog1("1")
  //  dog1.name
  val dog2 = new Dog2("2")
  dog2.name
  //  dog2.name = "c" <- val이라서 넣지 못한다.
  val dog3 = new Dog3("3")
  dog3.name
  dog3.name = "d"

//  case class CaseClass01(name: String, age: Int)

//  val test = CaseClass01("a", 10)

//  if (test.age > 17) println("A") else println("B")

  // 왼쪽에 객체가 나오게 된다.
  object CaseClass01 { // 동반 객체
//    def apply(name: String, age: Int): CaseClass01 = new CaseClass01(name, age)
//
//    def apply(name: String): CaseClass01 = new CaseClass01(name, 10)

    /**
     * caseClass 를 만들게 된다면 object가 쌍으로 생긴다.
     * object CaseClass01{    // 동반 객체
     * def apply(name: String, age: Int):CaseClass01 = new CaseClass01(name, age)
     *
     * 모든 Dto, Vo는 왠만하면 Cass Class으로 만들어 준다.
     * ShipmentUpdateCount
     * 동방 객체를 안 만들어도 되는데
     */
  }

//  val caseClassEx2 = CaseClass01("1", 10)
//  val caseClassEx3 = CaseClass01("dd")
  // 동방 객체가 object 여서 전부 알아서 Static 하게 접근 하게 된다.
  // Dto, Vo는 전부 매핑이 가능하다.

  // APP


  // 변수 선언, var, val 타입은 : 으로 메서드의 부분만 추출 한다.
  // Monad는 나중에
  // Class는 자바와 똑같다
  // 나머지는 with으로 붙인다.
  // 다른 건 생성자 메서드가 다르다.
  // 동방 객체에 직접 선언을 해도 된다.

}