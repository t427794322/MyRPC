package cn.itcast.implic

class ChooserC[T <% Ordered[T]] {
  def choose(first: T, second: T): T = {
    if (first > second) first else second
  }
}

object ChooserC {
  def main(args: Array[String]): Unit = {

    val g1 = new GirlC("pier", 9999)
    val g2 = new GirlC("fuck", 1888)

    import MyPredefC._

    println("test")

    val choose = new ChooserC[GirlC]



    val r = choose.choose(g1, g2)
    println(r.name)
  }
}