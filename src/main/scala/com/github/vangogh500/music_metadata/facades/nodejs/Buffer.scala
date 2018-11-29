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
}


/**
 * Buffer
 */
@js.native
@JSGlobal
object Buffer extends js.Object {
  def from(size: Int): Buffer = js.native
}
