package cn.itcast.implic

//class Chooser[T <% Ordered[T]] {
//  def choose(first: T, second: T): T = {
//    if (first > second) first else second
//  }
//}
//上下文界定
class Chooser[T : Ordering] {

  def choose(first: T, second: T): T = {
    val ord = implicitly[Ordering[T]]
    if (ord.gt(first, second)) first else second
  }
}

object Chooser {
  def main(args: Array[String]): Unit = {
    import MyPredefCh._
    val c = new Chooser[Girl]
    val g1 = new Girl("anglebaby", 190)
    val g2 = new Girl("hatano", 99)


    val g = c.choose(g1, g2)
    println(g.name)
  }
}