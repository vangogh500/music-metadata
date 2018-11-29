/**
 * Facade for Nodejs Buffer
 * @author Kai Matsuda
 */
package com.github.vangogh500.music_metadata
package facades
package nodejs

import scala.concurrent.{ Future, Promise, ExecutionContext }
import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

/**
 * Buffer
 */
@js.native
trait Buffer extends js.Object {
  def toString(encoding: String): String = js.native
  def toString(encoding: String, start: Int, end: Int): String = js.native
  def readUInt8(offset: Int): Int = js.native
  def readUInt16LE(offset: Int): Int = js.native
  def readUInt16BE(offset: Int): Int = js.native
  def readUInt32LE(offset: Int): Int = js.native
  def readUInt32BE(offset: Int): Int = js.native
  def readUIntBE(offset: Int, byteLength: Int): Int = js.native
  def readUIntLE(offset: Int, byteLength: Int): Int = js.native
}


/**
 * Buffer
 */
@js.native
@JSGlobal
object Buffer extends js.Object {
  def alloc(size: Int): Buffer = js.native
}
