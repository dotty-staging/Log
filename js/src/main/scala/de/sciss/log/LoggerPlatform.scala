/*
 *  LoggerPlatform.scala
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

import scala.scalajs.js

trait LoggerPlatform {
  final protected def mkTimeStamp(): String = {
    val d           = new js.Date()
    val year        = d.getFullYear().toInt
    val month       = d.getMonth().toInt + 1
    val dayOfMonth  = d.getDate().toInt

    val ld = f"$year%04d-$month%02d-$dayOfMonth%02d"

    val milli        = d.getMilliseconds().toInt
    val hour        = d.getHours().toInt
    val minute      = d.getMinutes().toInt
    val second      = d.getSeconds().toInt

    val lt: String = {
      val prefix      = f"$hour%02d:$minute%02d"
      val milliPart   = f".$milli%03d"
      val secondPart  = f":$second%02d"
      prefix + secondPart + milliPart
    }

    ld + " " + lt
  }
}
