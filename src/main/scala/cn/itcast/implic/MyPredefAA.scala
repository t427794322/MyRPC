package cn.itcast.implic

object MyPredefAA {
//  implicit def girl2Ordered(g: GirlA) = new Ordered[GirlA] {
//    override def compare(that: GirlA): Int = {
//      g.faceValue - that.faceValue
//    }
//  }

//  implicit val girl2Ordering = new Ordering[GirlA] {
//    override def compare(x: GirlA, y: GirlA): Int = {
//      x.faceValue - y.faceValue
//    }
//  }

    trait girl2Ordering extends Ordering[GirlA] {
      override def compare(x: GirlA, y: GirlA): Int = {
        x.faceValue - y.faceValue
      }
    }

    implicit object Girl extends girl2Ordering
}
