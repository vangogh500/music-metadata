/**
 * Facade for FS
 * @author Kai Matsuda
 */
package com.github.vangogh500.music_metadata
package facades
package fs

import scala.scalajs.js
import scala.concurrent.{Future, Promise, ExecutionContext}
import nodejs.{Error, Buffer}

/**
 * File system
 */
object FS {
  def open(path: String, flag: String): Future[Int] = {
    val p = Promise[Int]()
    Native.open(path, flag, (e: Error, fd: Int) => Option(e) match {
      case Some(e) => p failure e
      case None => p success fd
    })
    p.future
  }
  def read(fd: Int, buffer: Buffer, offset: Int, length: Int, position: Int): Future[(Int, Buffer)] = {
    val p = Promise[(Int, Buffer)]()
    Native.read(fd, buffer, offset, length, position, (e: Error, bytesRead: Int, buffer: Buffer) => Option(e) match {
      case Some(e) => p failure e
      case None => p success (bytesRead, buffer)
    })
    p.future
  }
  /**
   * Read directory
   * @param path Directory path
   * @return A list of directory names
   */
  def readdir(path: String)(implicit ec: ExecutionContext): Future[Seq[String]] = {
    val p = Promise[Seq[String]]()
    Native.readdir(path, (e: Error, items: js.Array[String]) => Option(e) match {
      case Some(e) => p failure e
      case None => p success items
    })
    p.future
  }
  /**
   * Get stats of directory
   * @param path Directory path
   * @return Stats of directory
   */
  def lstat(path: String)(implicit ec: ExecutionContext): Future[Stats] = {
    val p = Promise[Stats]()
    Native.lstat(path, (e: Error, stats: Stats) => Option(e) match {
      case Some(e) => p failure e
      case None => p success stats
    })
    p.future
  }
  /**
   * List files in directory (excludes folders)
   * @param path Directory path
   * @return A list of all files in directory
   */
  def dirfiles(path: String)(implicit ec: ExecutionContext): Future[Seq[String]] = readdir(path) flatMap { items =>
    Future.sequence(
      items map { item =>
        lstat(path + "\\" + item) flatMap { stats =>
          if(stats.isDirectory) dirfiles(path + "\\" + item)
          else Future { Seq(path + "\\" + item) }
        }
      }
    ) map(_.flatten)
  }
}
