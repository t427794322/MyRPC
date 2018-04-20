package cn.itcast.implic

object TestBoyA {
  def main(args: Array[String]): Unit = {
    val b1 = new BoyA("pier", 55)
    val b2 = new BoyA("james", 66)
    val arr = Array[BoyA](b1, b2)
    val sortedArr = arr.sortBy(x => x).reverse
    for (x <- sortedArr) {
      println(x.name)
    }
  }
}
