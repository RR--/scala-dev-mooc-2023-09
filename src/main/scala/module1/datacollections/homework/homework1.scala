package module1.datacollections.homework

import scala.util.Random

class BallsExperiment {
  private val rnd = new Random()
  private var bucket: List[Int] = List(0, 1, 0, 1, 0, 1)

  private def takeBall(): Int = {
    val n = rnd.nextInt(bucket.size)
    val res = bucket(n)
    bucket = bucket.take(n) ++ bucket.drop(n + 1)
    res
  }

  // вероятность сочетания: первый шар черный, второй шар белый
  def isFirstBlackSecondWhite(): Boolean = {
    val firstBall = takeBall()
    val secondBall = takeBall()
    firstBall == 0 && secondBall == 1
  }

  // если считаем что первый шар всегда был черный
//  def isFirstBlackSecondWhite(): Boolean = {
//    bucket = List(1, 0, 1, 0, 1)
//    val secondBall = takeBall()
//    secondBall == 1
//  }
}

object BallsTest {
  def main(args: Array[String]): Unit = {
    val count = 10000
    val listOfExperiments: List[BallsExperiment] = (1 to count).map(_ => new BallsExperiment).toList
    val countOfExperiments = listOfExperiments.map(_.isFirstBlackSecondWhite())
    val countOfPositiveExperiments: Float = countOfExperiments.count(_ == true)
    println(countOfPositiveExperiments / count)
  }
}