package cn.itcast.implic

//class ChooserA[T <% Ordered[T]] {
//  def choose(first: T, second: T): T = {
//    if (first > second) first else second
//  }
//}

class ChooserA[T : Ordering] {
  def choose(first: T, second: T): T = {
    val grd = implicitly[Ordering[T]]
    if (grd.gt(first, second)) first else second
  }
}

object ChooserA {
  def main(args: Array[String]): Unit = {

    import MyPredefAA._

    val g1 = new GirlA("mm", 5000)
    val g2 = new GirlA("angelbaby", 9999)
    val chA = new ChooserA[GirlA]

    val result = chA.choose(g1, g2)
    println(result.name)
  }
}
