package com.github.vangogh500.music_metadata

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import facades.fs._
import facades.nodejs._
import parsers.id3v2.ver3.{FrameHeader}
import parsers.MusicMetaParser

/**
 * Main
 */
object Main {
  def main(args: Array[String]): Unit = {
    MusicMetaParser("""D:\Media\Music\channel 23\cloud (2017)\1. Cloud.mp3""") foreach { parser =>
      parser.parse()
    }
    /*FS.open("""D:\Media\Music\channel 23\cloud (2017)\1. Cloud.mp3""", "r") foreach { fd =>
      TagHeader.extract(fd).foreach { header =>
        println(header)
      }
      FrameHeader.extract(fd).foreach { frame =>
        println(frame)
      }
    }*/
  }
}
