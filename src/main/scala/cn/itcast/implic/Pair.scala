package cn.itcast.implic

class Pair[T <: Comparable[T]] {

  def bigger(first : T, second: T): T = {
    if (first.compareTo(second) > 0) first else second
  }
}

object Pair {
  def main(args: Array[String]): Unit = {
    val p = new Pair[String]
    println(p.bigger("hadoop", "spark"))
  }
}
