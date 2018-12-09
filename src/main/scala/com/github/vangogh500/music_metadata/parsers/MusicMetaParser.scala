package com.github.vangogh500.music_metadata
package parsers

import scala.concurrent.{Future, ExecutionContext}
import parsers.mp3.MP3MetaParser

/**
 * Music metadata parser
 */
trait MusicMetaParser {
  def parse()(implicit ec: ExecutionContext): Unit
}

/**
 * Music metadata parser
 */
object MusicMetaParser {
  def apply(file: String)(implicit ec: ExecutionContext): Future[MusicMetaParser] = file match {
    case s if s.matches("""(?i)^.*\.mp3$""") => MP3MetaParser(file)
    case _ => throw new Exception("Invalid encoding type")
  }
}
