/**
 * Facade for FS
 * @author Kai Matsuda
 */
package com.github.vangogh500.music_metadata
package facades
package fs

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import nodejs.{Error, Buffer}

/**
 * Nodejs FS
 */
@js.native
@JSImport("fs", JSImport.Namespace)
object Native extends js.Object {
  def open(path: String, flag: String, callback: js.Function2[Error, Int, Unit]): Unit = js.native
  def read(fd: Int, buffer: Buffer, offset: Int, length: Int, position: Int, callback: js.Function3[Error, Int, Buffer, Unit]): Unit = js.native
  def readdir(path: String, callback: js.Function2[Error, js.Array[String], Unit]): Unit = js.native
  def lstat(path: String, callback: js.Function2[Error, Stats, Unit]): Unit = js.native
}
