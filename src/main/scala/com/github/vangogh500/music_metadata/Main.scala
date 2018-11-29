package com.github.vangogh500.music_metadata

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import facades.fs._
import facades.nodejs._


/**
 * Main
 */
object Main {
  def main(args: Array[String]): Unit = {
    FS.open("""D:\Media\Music\channel 23\cloud (2017)\1. Cloud.mp3""", "r") foreach { fd =>
      val b = Buffer.alloc(10)
      FS.read(fd, b, 0, 10, 0) foreach {
        case (i, buff) =>
          println(buff.toString("utf8", 0, 3))
          println(buff.toString("hex", 3, 5))
          println(buff.toString("hex", 3, 5))
          println(buff.toString("hex", 6, 10))
          val i = Array.tabulate(4)(i => buff.readUInt8(6 + i)).reduceLeft((a, b) => {
            val mask = b << 6 
          })
          println()
      }
    }
  }
}
