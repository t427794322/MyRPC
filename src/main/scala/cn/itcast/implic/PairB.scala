package cn.itcast.implic

class PairB[T <: Comparable[T]] {
  def bigger(first: T, second: T): T = {
    if (first.compareTo(second) > 0) first else second
  }
}

object PairB {
  def main(args: Array[String]): Unit = {
    val p = new PairB[Integer]
    val result = p.bigger(1, 2)
    println(result)
  }
}