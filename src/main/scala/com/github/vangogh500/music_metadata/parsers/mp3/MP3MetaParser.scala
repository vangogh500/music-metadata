package com.github.vangogh500.music_metadata
package parsers
package mp3

import scala.concurrent.{Future, ExecutionContext}
import facades.fs.FS
import facades.nodejs.Buffer

import id3v2.ID3V2Parser

/**
 * Mp3 Meta Parser
 */
trait MP3MetaParser extends MusicMetaParser

/**
 * Mp3 Meta Parser
 */
object MP3MetaParser {
  def future(fd: Int)(implicit ec: ExecutionContext): Future[MP3MetaParser] = {
    val b = Buffer.alloc(3)
    FS.read(fd, b, 0, 3, 0) flatMap {
      case (_, buff) =>
        if(buff.toString("utf8", 0, 3) == "ID3") ID3V2Parser.future(fd)
        else throw new Exception("Invalid encoding type")
    }
  }
}
