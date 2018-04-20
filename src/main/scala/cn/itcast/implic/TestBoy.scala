package cn.itcast.implic

object TestBoy {
  def main(args: Array[String]): Unit = {
    val b1 = new Boy("laoduan", 99)
    val b2 = new Boy("laozhao", 999)

    val arr = Array[Boy](b1, b2)
    var sorted = arr.sortBy(x => x).reverse
    for (x <- sorted) {
      println(x.name)
    }
  }
}
