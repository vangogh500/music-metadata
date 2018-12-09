package com.github.vangogh500.music_metadata
package parsers
package mp3
package id3v2
package ver3

import scala.concurrent.{Future, ExecutionContext}

/**
 * ID3v2.3.* parser
 */
class ID3V23Parser(fd: Int) extends ID3V2Parser {
  def parse()(implicit ec: ExecutionContext): Unit = {
    Header.parse(fd) foreach { h =>
      println(h)
    }
  }
}
