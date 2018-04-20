package cn.itcast.implic
import MyPredefB._

//class ChooserB[T <% Ordered[T]] {
//  def choose(first: T, second: T): T = {
//    if (first > second) first else second
//  }
//}

class ChooserB[T: Ordering] {
  def choose(first: T, second: T): T = {
    val ord = implicitly[Ordering[T]]
    if (ord.gt(first, second)) first else second
  }
}

object ChooserB {
  def main(args: Array[String]): Unit = {


    val g1 = new GirlB("feichu", 999)
    val g2 = new GirlB("wocao", 133)
    val choose = new ChooserB[GirlB]




    val r = choose.choose(g1, g2)
    println(r.name)
  }
}