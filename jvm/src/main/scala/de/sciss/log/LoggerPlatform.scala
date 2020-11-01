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

import java.time.{Clock, LocalDate}

trait LoggerPlatform {
  private[this] val clock = Clock.systemDefaultZone
  private[this] val rules = clock.getZone.getRules

  final protected def mkTimeStamp(): String = {
    val instant           = clock.instant()
    val offset            = rules.getOffset(instant)
    val localSecond       = instant.getEpochSecond + offset.getTotalSeconds
    val SECONDS_PER_DAY   = 86400
    val NANOS_PER_SECOND  = 1000000000L
    val NANOS_PER_MINUTE  = 60000000000L
    val NANOS_PER_HOUR    = 3600000000000L
    val localEpochDay     = Math.floorDiv(localSecond, SECONDS_PER_DAY)
    val secsOfDay         = Math.floorMod(localSecond, SECONDS_PER_DAY)
    val ld                = LocalDate.ofEpochDay(localEpochDay)
    var nanoOfDay         = secsOfDay * NANOS_PER_SECOND + instant.getNano
    val hour              = (nanoOfDay / NANOS_PER_HOUR)  .toInt; nanoOfDay -= hour   * NANOS_PER_HOUR
    val minute            = (nanoOfDay / NANOS_PER_MINUTE).toInt; nanoOfDay -= minute * NANOS_PER_MINUTE
    val second            = (nanoOfDay / NANOS_PER_SECOND).toInt; nanoOfDay -= second * NANOS_PER_SECOND
    val nano              = nanoOfDay.toInt

    val sb = new java.lang.StringBuilder(26)
    sb.append(ld.toString).append(' ')

    sb.append(if (hour   < 10)  "0" else  "").append(hour)
      .append(if (minute < 10) ":0" else ":").append(minute)
      .append(if (second < 10) ":0" else ":").append(second)
      .append('.')
      .append(Integer.toString((nano / 1000000) + 1000).substring(1))  // milliseconds

    sb.toString
  }
}
