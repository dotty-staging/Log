package de.sciss.log

object Demo extends App {
  val log = new Logger("demo", Level.Info)
  log.debug("this is swallowed"   )
  log.info ("this is information" )
  log.warn ("this is a warning"   )
  log.error("this is an error"    )
}
