/*
 *  Logger.scala
 *  (Log)
 *
 *  Copyright (c) 2020 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is published under the GNU Lesser General Public License v2.1+
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.log

import java.io.PrintStream

import de.sciss.log.Level._

final class Logger(
                    var id        : String,
                    var level     : Int         = Level.Warn,
                    var out       : PrintStream = Console.err,
                    var timeStamp : Boolean     = true,
                  )
  extends LoggerPlatform {

  def debug(msg: => String): Unit = if (level <= Debug) doLog("debug" , msg)
  def info (msg: => String): Unit = if (level <= Info ) doLog("info"  , msg)
  def warn (msg: => String): Unit = if (level <= Warn ) doLog("warn"  , msg)
  def error(msg: => String): Unit = if (level <= Error) doLog("error" , msg)

  private def doLog(levelS: String, msg: String): Unit = {
    val str = if (timeStamp) {
      "[" + mkTimeStamp() + "] [" + levelS + "] '" + id + "' " + msg
    } else {
      "[" + levelS + "] '" + id + "' " + msg
    }
    out.println(str)
  }
}
