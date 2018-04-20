package cn.itcast.implic

import java.io.File

import scala.io.Source

object MyPredefA {
  implicit def fileToRichFileA(f: File) = new RichFile(f)
}

class RichFileA(val f: File) {
  def read() = Source.fromFile(f).mkString
}

object RichFileA {
  def main(args: Array[String]): Unit = {
    val f = new File("/Users/pier/Desktop/test/words2.txt")
    import MyPredefA._
    val content = f.read
    println("fuck")
    println(content)
  }
}
