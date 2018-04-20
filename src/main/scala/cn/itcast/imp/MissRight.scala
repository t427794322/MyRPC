package cn.itcast.imp

class MissRight[T] {
  def choose(first: T, second: T)(implicit ord: T => Ordered[T]): T = {
    if (first > second) first else second
  }

  def select(first: T, second: T)(implicit ord: Ordering[T]): T = {
    if (ord.gt(first, second)) first else second
  }

  def random(first: T, second: T)(implicit ord: Ordering[T]): T = {
    import Ordered.orderingToOrdered
    if (first > second) first else second
  }
}

object MissRight {
  def main(args: Array[String]): Unit = {
    val g1 = new GirlF("kitty", 88, 99)
    val g2 = new GirlF("daianna", 88, 55)
    val missRight = new MissRight[GirlF]

    import MyPredefF._
    val result = missRight.random(g1, g2)
    println(result.name)
  }
}
