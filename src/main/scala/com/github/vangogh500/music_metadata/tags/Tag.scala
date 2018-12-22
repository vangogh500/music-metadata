package com.github.vangogh500.music_metadata
package tags

trait Tag[T] {
  val value: T
}

object Tag {
  case class Album(value: String) extends Tag[String]
}
