package com.scala.code.research

import com.scala.code.research.collection.list7

// VO와 DTO는 모두 case Class

case class Test
(
  test: Int,
  string: String,
  double: Double
)

object Test {}

/* null은 무조건 optional을 Setting 해야 한다*/
/* empty일 때 마다 기본 값을 넣어 주어야 한다.*/
object patternMatch extends App {
  val test = Test.apply(1, "a", 0.0)
  /* unapply가 없어도 case Class으로 자동으로 만들어 준다. */
  /* pattern matching 이 unapply가 있어야 가능하다. */
  //  Test.unapply()
  /**
   * case (Test(0, _, _) =>
   * case (test @ Test(0, _, _) =>
   * _ 는 어떤 값이든지 상관이 없는 경우
   * pattern matching 가디언
   * case (left, right) => if right.statusCount.size > left.statusCount.size
   * 마지막에 matching이 되는 것이 없다고 하면 exhaustive
   * - parsell function -> x, y = 공역과 치역 어떤 함수에 적용을 했더니 적용이 안되는
   * - 조건에 만족하지 않는 것에 대해서 에러가 없으면 부분 매칭이 생기게 된다.
   * - 필요 충분하게 적용을 해야 한다
   * - 패턴 매칭인 경우 class class의 맴벼 변수의 조건을 마음대로 사용할 수 있다.
   */
}

object collection extends App {
  /**
   * Default - 변경 불가능
   *
   * scala Coolection
   * 최상의 Traversable가 있다.
   * Seq - 순번 (List) - IndexSeq(Vector, String , Range) / LinearSeq(List, Stack, Stream, Queue)
   *  - Vector - Search 기능에 있어서 기능에 있다. / List 찾는 방법에 따라서 다르다.
   *    Set
   *    Map
   */

  val list = List(1, 2, 3) //  사실상 변경이 불가능 하다. immutable이 기본적으로 되어 있다. import를 선언하지 않고 사용한다면 immutable이다.
  val list2 = 4 :: list // immutable 떄문에 기존에 변경이 되지 않고 새롭게 만들어 진다.
  val list3 = 5 :: list // 4 도 추가되는 것이 아니라 1,2,3 리스트에 계속해서 더하는 것이다.
  6 :: list // (list.::(6)) 같은 의미
  /* 내부적으로 메모리가 문제가 생기지 않는다, 메모리를 이전 것을 참조 한다 */
  /* 새로운 리스트를 만들어도 기존의 리스트를 참조 하면서 진행 한다 */
  val vector = Vector(1, 2, 3)

  case class TestBean(value: Int) {
    def toPlusOne(targetValue: Int): Int = value + targetValue
  }

  val testBean = TestBean(10) // 이 메서드는 사실상 apply 실행이 된 것이다
  val updateAge = testBean toPlusOne 5 // 서술형으로도 사용이 가능하도록
  // :: 는 반대로 뒤에서 메서드를 상관 한다. 이유는 앞에다가 붙기 때문이다 일부러 순서 앞에다가 붙이도록 인식하도록 순서를 바꾼 것이다.

  val list4 = list :+ 5 // 이 것은 반대로 뒤에다가 붙이는 것이다.
  // 이것은 시간 복잡도가 O(1)으로 된다.
  // Nil - 껍데기에 아무 것도 없는 것이다. List 아레에 잇는 하이라키
  val list5 = 1 :: Nil // 콘즈 메서드에 아무 것도 없는 것도 추가할 수 있다.
  val list6 = 1 :: 2 :: Nil // 순서가 뒤에서 부터 앞으로 진행을 하게 된다.

  // List => head::tail 구조로 되어 있다.
  // List(1,2,3,4,5) => 1::tail => 1::(2::tail) => 1::(2::(3::tail)) =..=> 1::2::3::4::5::Nil 구조로 되어 있다.
  // head 접근만 할 수 있다. O(1)을 알 수 있다. 왼쪽 부터 접근을 하게 된다면 List를 쓰는 것이 빠르다
  val list7 = List(1, 2, 3, 4, 5, 6, 7)
  val value = list7.foldLeft(0)((list7, it) => list7 + it) // 1부터 왼쪽 까지 합치는 것
  val map8 = list7.foldLeft(Map.empty[String, Int])((list7, it) => list7.updated(it.toString, it))
  val map9 = list7.foldLeft(Map.empty[String, Int]) {
    /*새로운 메모리로 계속 추가를 하게 되지만 계속 메모리를 추가로 참조하기 때문에 변하지 않는다.*/
    (list7, it) => list7.updated(it.toString, it)
  }

  val list9 = list7.foldRight(0)((list7, it) => list7 + it)
  // 시간 복잡도가 가장 최악으로 간다. 이때는 Vector를 쓰는 것이 훨씬 좋다.
  // List와 반대로 구현되어 있는 구현체는 없다

  //  val value = list7.foldLeft(0)((list7, it) => list7 + it) // 1부터 왼쪽 까지 합치는 것
  val value2 = list7.foldLeft(0)(_ + _) // 앞은 초기 값, 뒤는 추가할 값을 가르킨다.

  val map = Map("a" -> 1, "b" -> 2) // HashMap이 Default
  val map2 = map + Tuple2("c", 3)
  val map3 = map.updated("b", 3)
}

object implicitObject extends App {
  // 가독성이 매우 좋지만, 이해하기 힘들지만
  // 이 것을 쓰게 되면 implicit는 쌓을 때 좋지 않다.
  // 이때는 Utils에 주로 사용을 하게 된다.
  // impli

  implicit class IMPL(value: Int) {
    def |+|(target: Int) = value + target
  }

  val test = 1 |+| 2
}

object cats extends App {
  /**
   * monad와 같은 것들을 추가하는 것들
   */
}
