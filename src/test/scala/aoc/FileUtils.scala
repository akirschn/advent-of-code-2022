package aoc

import scala.io.Source

object FileUtils {

  def load(path: String): List[String] = {
    val fileStream = getClass.getResourceAsStream(path)
    val lines = Source.fromInputStream(fileStream).getLines.toList
    fileStream.close()
    lines
  }
}
