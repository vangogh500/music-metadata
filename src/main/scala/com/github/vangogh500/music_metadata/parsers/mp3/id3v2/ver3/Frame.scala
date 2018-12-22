package com.github.vangogh500.music_metadata
package parsers
package mp3
package id3v2
package ver3

import scala.concurrent.{Future, ExecutionContext}
import facades.nodejs.Buffer
import facades.fs.FS
import tags.Tag

object Frame {
  /**
   * Parses the ID3v2.3.* frame
   * @param fd FS file identifier
   */
  def parse(fd: Int, pos: Int)(implicit ec: ExecutionContext): Future[Tag[String]] = {
    FrameHeader.parse(fd, pos) flatMap {
      case FrameHeader(id, frameSize, flags) =>
        println(flags)
        println(id)
        val b = Buffer.alloc(frameSize)
        FS.read(fd, b, 0, frameSize, pos + 10) map {
          case (_, buff) =>
          val encoding = buff.toString("hex", 0, 1)
          println(encoding)
          Tag.Album(buff.toString("utf8", 1, frameSize -1))
        }
    }
  }
}
