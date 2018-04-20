package cn.itcast.implic

object MyPredefC {
  implicit def girlC2Ordered(g: GirlC) = new Ordered[GirlC] {
    override def compare(that: GirlC): Int = {
      g.faceValue - that.faceValue
    }
  }
}
