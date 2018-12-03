package com.github.vangogh500.music_metadata
package parsers
package id3v2
package ver3

import scala.concurrent.{Future, ExecutionContext}
import facades.nodejs.Buffer
import facades.fs.FS

/**
 * ID3V2 Tag Header
 * @see http://id3.org/id3v2.3.0
 * @constructor
 * @param version ID3V2 version i.e. ID3V2.3.0 == "0300"
 * @param tagSize Number of bytes of the tag
 */
case class TagHeader(version: String, tagSize: Int)

/**
 * ID3V2 Header
 * @see http://id3.org/id3v2.3.0
 */
object TagHeader {
  /**
   * Exract ID3V2 header using fd.
   * @param fd File determinator for fs
   */
  def extract(fd: Int)(implicit ec: ExecutionContext): Future[TagHeader] = {
    val b = Buffer.alloc(10)
    FS.read(fd, b, 0, 10, 0) map {
      case (_, buff) =>
        if(buff.toString("utf8", 0, 3) == "ID3") {
          TagHeader(
            version = buff.toString("hex", 3, 5),
            tagSize = Array.tabulate(4)(i => {
              val byte = buff.readUInt8(6 + i)
              val pos = 3 - i
              byte << (7 * pos)
            }).reduceLeft(_ + _)
          )
        } else {
          throw new Exception("Invalid ID3V2 file")
        }
    }
  }
}
