package cn.itcast.implic

object MyPredefB {
  implicit val girlToOrdered = (g: GirlB) => new Ordered[GirlB] {
    override def compare(that: GirlB): Int = {
      g.faceValue - that.faceValue
    }
  }

}
