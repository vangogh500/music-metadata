package com.github.vangogh500.music_metadata
package parsers
package mp3
package id3v2
package ver3

import scala.concurrent.{Future, ExecutionContext}
import facades.fs.FS
import facades.nodejs.Buffer

/**
 * ID3v2.3.* header
 * @constructor
 * @param flags Header flags
 * @param size ID3 tag size in bytes
 */
case class Header(
  flags: HeaderFlags,
  size: Int
)

/**
 * ID3v2.3.* header flags
 * @constructor
 * @param unsynchronisation Unsynchronization used
 * @param extendedHeader Extended header used
 * @param experimental Experimental used
 */
case class HeaderFlags(
  unsynchronisation: Boolean,
  extendedHeader: Boolean,
  experimental: Boolean
)

/**
 * ID3v2.3.* header
 */
object Header {
  /**
   * Parses the ID3v2.3.* header from a file
   * @param fd FS file identifier
   */
  def parse(fd: Int)(implicit ec: ExecutionContext): Future[Header] = {
    val b = Buffer.alloc(5)
    FS.read(fd, b, 0, 5, 5) map {
      case (_, buff) =>
        val flags = buff.readUInt8(0)
        Header(
          HeaderFlags(
            unsynchronisation = (128 & flags) != 0,
            extendedHeader = (64 & flags) != 0,
            experimental = (32 & flags) != 0
          ),
          size = Array.tabulate(4)(i => {
            val byte = buff.readUInt8(1 + i)
            val pos = 3 - i
            byte << (7 * pos)
          }).reduceLeft(_ + _)
        )
    }
  }
}
