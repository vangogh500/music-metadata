package com.github.vangogh500.music_metadata
package parsers
package id3v2
package ver3

import scala.concurrent.{Future, ExecutionContext}
import facades.nodejs.Buffer
import facades.fs.FS

/**
 * ID3V2 Frame Header
 * @see http://id3.org/id3v2.3.0
 * @constructor
 * @param id Frame id
 * @param frameSize Size of the frame
 */
case class FrameHeader(title: String, frameSize: Int)

/**
 * ID3V2 Frame Header
 * @see http://id3.org/id3v2.3.0
 */
object FrameHeader {
  /**
   * Exract ID3V2 header using fd.
   * @param fd File determinator for fs
   */
  def extract(fd: Int)(implicit ec: ExecutionContext): Future[FrameHeader] = {
    val b = Buffer.alloc(10)
    FS.read(fd, b, 0, 10, 10) map {
      case (_, buff) =>
        FrameHeader(
          title = buff.toString("utf8", 0, 4),
          frameSize = buff.toString("hex", 4, 8).toInt
        )
    }
  }
}
