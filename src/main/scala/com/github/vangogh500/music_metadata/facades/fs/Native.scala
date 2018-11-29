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
  /**
   * Async file open
   * @param path file path
   * @param flag file system flag - 'r' reading, 'w' writing, etc
   * @param callback callback
   */
  def open(path: String, flag: String, callback: js.Function2[Error, Int, Unit]): Unit = js.native
  /**
   * Read data from a file
   * @param fd file specifier
   * @param buffer buffer that the data will be written to
   * @param offset offset in the buffer to start writing at
   * @param length number of bytes to read
   * @param position where to begin reading
   */
  def read(fd: Int, buffer: Buffer, offset: Int, length: Int, position: Int, callback: js.Function3[Error, Int, Buffer, Unit]): Unit = js.native
  /**
   * Reads the contents of a directory
   * @param path file path
   * @param callback callback
   */
  def readdir(path: String, callback: js.Function2[Error, js.Array[String], Unit]): Unit = js.native
  /**
   * Get file stats
   * @param path file path
   * @param callback callback
   */
  def lstat(path: String, callback: js.Function2[Error, Stats, Unit]): Unit = js.native
}
