package cn.itcast.implic

object MyPredefCh {

  implicit val girl2Ordered = (g: Girl) => new Ordered[Girl] {
    override def compare(that: Girl): Int = {
      g.faceValue - that.faceValue
    }
  }
}
