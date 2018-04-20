package cn.itcast.implic

object TestBoyB {
  def main(args: Array[String]): Unit = {
    val b1 = new BoyB("pier", 100)
    val b2 = new BoyB("james", 300)
    val arr = Array[BoyB](b1, b2)
    val sortedArr = arr.sortBy(x => x).reverse
    for (x <- sortedArr) {
      println(x.name)
    }
  }
}
