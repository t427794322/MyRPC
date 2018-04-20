package cn.itcast.imp

object MyPredefD {
//  implicit def girl2Order(g: GirlD) = new Ordered[GirlD] {
//    override def compare(that: GirlD): Int = {
//      if (g.faceValue == that.faceValue) {
//        that.size - g.size
//      } else {
//        g.faceValue - that.faceValue
//      }
//    }
//  }

//  implicit def girl2Ordering = new Ordering[GirlD] {
//    override def compare(x: GirlD, y: GirlD): Int = {
//      if (x.faceValue == y.faceValue) {
//        y.size - x.size
//      } else {
//        x.faceValue - y.faceValue
//      }
//    }
//  }

//  implicit object girl2Ordering extends Ordering[GirlD] {
//    override def compare(x: GirlD, y: GirlD): Int = {
//      if (x.faceValue == y.faceValue) {
//        y.size - x.size
//      } else {
//        x.faceValue - y.faceValue
//      }
//    }
//  }

  implicit def girl2Ordered(g: GirlD) = new Ordered[GirlD] {
    override def compare(that: GirlD): Int = {
      if (g.faceValue == that.faceValue) {
        that.size - g.size
      } else {
        g.faceValue - that.faceValue
      }
    }
  }

//  trait girl2Ordering extends Ordering[GirlD] {
//    override def compare(x: GirlD, y: GirlD): Int = {
//      if (x.faceValue == y.faceValue) {
//        y.size - x.size
//      } else {
//        x.faceValue - y.faceValue
//      }
//    }
//  }
//
//  implicit object g2order extends girl2Ordering
}
