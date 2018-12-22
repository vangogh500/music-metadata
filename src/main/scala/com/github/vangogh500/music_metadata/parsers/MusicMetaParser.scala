package com.github.vangogh500.music_metadata
package parsers

import scala.concurrent.{Future, ExecutionContext}
import facades.fs.FS
import parsers.mp3.MP3MetaParser

/**
 * Music metadata parser
 */
trait MusicMetaParser {
  val fd: Int
  def parse()(implicit ec: ExecutionContext): Unit
}

/**
 * Music metadata parser
 */
object MusicMetaParser {
  def future(file: String)(implicit ec: ExecutionContext): Future[MusicMetaParser] =
    for {
      fd <- FS.open(file, "r")
      parser <- file match {
        case s if s.matches("""(?i)^.*\.mp3$""") => MP3MetaParser.future(fd)
        case _ => throw new Exception("Invalid encoding type")
      }
    } yield parser
}
