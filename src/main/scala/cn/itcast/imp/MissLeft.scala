package cn.itcast.imp

//class MissLeft[T <% Ordered[T]] {
//  def choose(first: T, second: T): T = {
//    if (first > second) first else second
//  }
//}

class MissLeft[T : Ordering] {
//  def choose(first: T, second: T): T = {
//    val ord = implicitly[Ordering[T]]
//    if (ord.gt(first, second)) first else second
//  }

//  def select(first: T, second: T)(implicit ord: T => Ordered[T]): T = {
//    if (first > second) first else second
//  }

  def choose(first: T, second: T)(implicit ord: Ordering[T]): T = {
    if (ord.gt(first, second)) first else second
  }

//  def chooseOrdeed(first: T, second: T)(implicit ord: Ordering[T]): T = {
//    import Ordered.orderingToOrdered
//    if (first > second) first else second
//  }

//  def random(first: T, second: T)(implicit ord : Ordering[T]): T ={
//    import Ordered.orderingToOrdered
//    if(first > second) first else second
//  }
}

object MissLeft{
  def main(args: Array[String]): Unit = {
    val g1 = new GirlD("a1", 97, 31)
    val g2 = new GirlD("a2", 97, 32)

    import MyPredefD._
    val missLeft = new MissLeft[GirlD]
    val result = missLeft.choose(g1, g2)
    println(result.name)
  }
}
