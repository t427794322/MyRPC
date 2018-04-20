package cn.itcast.implic

import java.io.File

import scala.io.Source

object MyPredef {
  implicit def fileToRichFile(file: File) = new RichFile(file)
}

class RichFile(val file: File) {
  def read() = Source.fromFile(file).mkString
}

object RichFile {
  def main(args: Array[String]): Unit = {
    val f = new File("/Users/pier/Desktop/test/words2.txt")
    //val content = new RichFile(f).read()
    import MyPredef.fileToRichFile
    val content = f.read
    println(content)
  }
}
