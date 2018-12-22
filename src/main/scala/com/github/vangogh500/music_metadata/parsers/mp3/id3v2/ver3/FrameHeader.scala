package com.github.vangogh500.music_metadata
package parsers
package mp3
package id3v2
package ver3

import scala.concurrent.{Future, ExecutionContext}
import facades.nodejs.Buffer
import facades.fs.FS

/**
 * ID3v2.3.* Frame header
 * @see http://id3.org/id3v2.3.0
 * @constructor
 * @param id Frame id
 * @param frameSize Size of the frame
 */
case class FrameHeader(id: String, frameSize: Int, flags: FrameHeaderFlags)

/**
 * ID3v2.3.* Frame header flags
 * @see http://id3.org/id3v2.3.0
 * @constructor
 * @param id Frame id
 * @param frameSize Size of the frame
 */
case class FrameHeaderFlags(
  tagAlterPreservation: Boolean,
  fileAlterPreservation: Boolean,
  readOnly: Boolean,
  compression: Boolean,
  encryption: Boolean,
  grouping: Boolean
)

/**
 * ID3V2 Frame Header
 * @see http://id3.org/id3v2.3.0
 */
object FrameHeader {
  /**
   * Exract ID3V2 header using fd.
   * @param fd File determinator for fs
   */
  def parse(fd: Int, pos: Int)(implicit ec: ExecutionContext): Future[FrameHeader] = {
    val b = Buffer.alloc(10)
    FS.read(fd, b, 0, 10, pos) map {
      case (_, buff) =>
        val flags1 = buff.readUInt8(8)
        val flags2 = buff.readUInt8(9)
        println(buff.toString("hex", 4, 8))
        FrameHeader(
          buff.toString("utf8", 0, 4),
          buff.toString("hex", 4, 8).toInt,
          FrameHeaderFlags(
            tagAlterPreservation = (128 & flags1) == 0,
            fileAlterPreservation = (64 & flags1) == 0,
            readOnly = (32 & flags1) != 0,
            compression = (128 & flags2) != 0,
            encryption = (64 & flags2) != 0,
            grouping = (32 & flags2) != 0
          )
        )
    }
  }
}
