import scala.collection.mutable
import scala.io.StdIn

object Main extends App {

  val n = StdIn.readInt()
  def canNotMove(x: Int, y: Int): Boolean = {
    (x < 0 || y < 0 || x >= n || y >= n)
  }
  val news = Array((-2, -1), (-2, 1), (0, -2), (0, 2), (2, -1), (2, 1))
  val array = Array.fill(n, n)(Int.MaxValue)
  val st = StdIn.readLine().split(" ")
  var first = Tuple2[Int, Int](st(0).toInt, st(1).toInt)
  val second = Tuple2[Int, Int](st(2).toInt, st(3).toInt)

  val queue = new mutable.Queue[(Int, Int)]()
  var answer = -1
  array(first._1)(first._2) = 0
  queue.enqueue(first)
  while(queue.nonEmpty){
    val now = queue.dequeue()
    var validation = true
    if(now.equals(second)){
      answer = array(now._1)(now._2)
      validation = false
    }
    if(validation){
      news.foreach( dir => {
        val nx = now._1 + dir._1
        val ny = now._2 + dir._2
        if(!canNotMove(nx, ny) && array(nx)(ny) > array(now._1)(now._2)){
          queue.enqueue((nx, ny))
          array(nx)(ny) = array(now._1)(now._2) + 1
        }
      })
    }
  }
  println(answer)
}