package com.github.vangogh500.music_metadata
package parsers
package mp3
package id3v2

import scala.concurrent.{Future, ExecutionContext}
import facades.fs.FS
import facades.nodejs.Buffer

import ver3.ID3V23Parser

/**
 * ID3 version 2 parser
 */
trait ID3V2Parser extends MP3MetaParser

/**
 * ID3 version 2 parser
 */
object ID3V2Parser {
  def apply(fd: Int)(implicit ec: ExecutionContext): Future[ID3V2Parser] = {
    val b = Buffer.alloc(2)
    FS.read(fd, b, 0, 2, 3) map {
      case (_, buff) => buff.toString("hex", 0, 2) match {
        case "0300" => new ID3V23Parser(fd)
        case _ => throw new Exception("Invalid encoding type")
      }
    }
  }
}
