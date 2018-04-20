package cn.itcast.implic

object ContextA {
  implicit val kk = "ronaldo"
}

object ImplicitValueA {

  def sayHi(implicit name: String="alice"): Unit = {
    println(name)
  }

  def main(args: Array[String]): Unit = {
    import ContextA._
    sayHi
  }
}
