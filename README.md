# Log

[![Build Status](https://travis-ci.org/Sciss/Log.svg?branch=main)](https://travis-ci.org/Sciss/Log)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.sciss/log_2.13/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.sciss/log_2.13)

## statement

Log is a very small Scala library for logging that works both on the JVM and on JavaScript (Scala.js).
It is (C)opyright 2020 by Hanns Holger Rutz. All rights reserved.
This project is released under
the [GNU Lesser General Public License](https://git.iem.at/sciss/Log/raw/main/LICENSE) v2.1+ and comes
with absolutely no warranties. To contact the author, send an e-mail to `contact at sciss.de`.

## requirements / installation

This project builds with sbt against Scala 2.13, 2.12, Dotty (JVM) and Scala 2.13 (JS).

To use the library in your project:

    "de.sciss" %% "log" % v

Use `%%%` with Scala.js cross project. The current version `v` is `"0.1.0"`.

## getting started

Please see `Demo.scala` in the test sources, or run `sbt rootJVM/test:run` or `sbt rootJS/test:run`.
Basically:

```scala
import de.sciss.log._

val log = new Logger("demo", Level.Info, Console.err)
log.debug("this is swallowed"   )
log.info ("this is information" )
log.warn ("this is a warning"   )
log.error("this is an error"    )
```

The print-out of this is

```
[2020-11-01 23:55:33.560] [info] 'demo' this is information
[2020-11-01 23:55:33.561] [warn] 'demo' this is a warning
[2020-11-01 23:55:33.561] [error] 'demo' this is an error
```

No more, no less. Look ma', no macros, no Java libraries.
Time-stamps are at millisecond granularity, this just reflects my preference.
You can turn time-stamps off by passing argument `timeStamp = false` to the constructor.
