package cn.itcast.implic

class BoyA(val name: String, val faceValue: Int) extends Comparable[BoyA]{
  override def compareTo(o: BoyA): Int = {
    faceValue - o.faceValue
  }
}
