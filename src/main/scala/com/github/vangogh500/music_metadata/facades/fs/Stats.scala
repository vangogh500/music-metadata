/**
 * Facade for FS Stats
 * @author Kai Matsuda
 */
package com.github.vangogh500.music_metadata
package facades
package fs

import scala.concurrent.{ Future, Promise, ExecutionContext }
import scala.scalajs.js

/**
 * Directory Stats
 */
@js.native
trait Stats extends js.Object {
  /**
   * Tests whether directory is file
   */
  def isFile(): Boolean = js.native
  /**
   * Tests whether directory is a folder
   */
  def isDirectory(): Boolean = js.native
}
