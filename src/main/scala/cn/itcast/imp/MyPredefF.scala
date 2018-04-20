package cn.itcast.imp

object MyPredefF {
//  implicit def girlF2Ordered(g: GirlF) = new Ordered[GirlF] {
//    override def compare(that: GirlF): Int = {
//      if (g.faceValue == that.faceValue) {
//        g.size - that.size
//      } else {
//        that.faceValue - g.faceValue
//      }
//    }
//  }

//  implicit def girlF2Ordering = new Ordering[GirlF] {
//    override def compare(x: GirlF, y: GirlF): Int = {
//      if (x.faceValue == y.faceValue) {
//        x.size - y.size
//      } else {
//        x.faceValue - y.faceValue
//      }
//    }
//  }

//    implicit val gril2Ordering = new Ordering[GirlF] {
//      override def compare(x: GirlF, y: GirlF): Int = {
//        if (x.faceValue == y.faceValue) {
//          x.size - y.size
//        } else {
//          x.faceValue - y.faceValue
//        }
//      }
//    }

//    trait girl2Ordering extends Ordering[GirlF] {
//      override def compare(x: GirlF, y: GirlF): Int = {
//        if (x.faceValue == y.faceValue) {
//          x.size - y.size
//        } else {
//          x.faceValue - y.faceValue
//        }
//      }
//    }
//
//    implicit object g2o extends girl2Ordering

  implicit object girl2Ordering extends Ordering[GirlF] {
    override def compare(x: GirlF, y: GirlF): Int = {
      if (x.faceValue == y.faceValue) {
        x.size - y.size
      } else {
        x.faceValue - y.faceValue
      }
    }
  }

}
