package com.github.vangogh500.music_metadata

import scala.concurrent.ExecutionContext.Implicits.global
import facades.fs._

/**
 * Main
 */
object Main {
  def main(args: Array[String]): Unit = {
    FS.open("""D:\Media\Music\channel 23\cloud (2017)\1. Cloud.mp3""", "r") map { fd =>
      println(fd)
    }
  }
}
