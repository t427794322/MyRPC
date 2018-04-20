package cn.itcast.implic

class PairA[T <: Comparable[T]] {
  def bigger(first: T, second: T): T = {
    if (first.compareTo(second) > 0) first else second
  }
}

object PairA {
  def main(args: Array[String]): Unit = {
    val p = new PairA[Integer]
    println(p.bigger(55, 32))
  }
}