package cn.itcast.implic

object Context {
  implicit val aa: String = "fuckfuck"
}

object ImplicitValue {

  def sayHi(implicit name: String = "laoduan"): Unit = {
    println(name)
  }

  def main(args: Array[String]): Unit = {
    import Context._
    sayHi
  }
}
