package cn.itcast.implic

class BoyB(val name: String, val faceValue: Int) extends Comparable[BoyB] {
  override def compareTo(o: BoyB): Int = {
    faceValue - o.faceValue
  }
}
