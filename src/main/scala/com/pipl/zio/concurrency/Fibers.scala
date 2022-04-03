package com.pipl.zio.concurrency

import zio._

object Fibers extends ZIOAppDefault {
  def printThread = s"[${Thread.currentThread().getName}]"

  val bathTime        = ZIO.succeed("Going to the bathroom")
  val boilingWater    = ZIO.succeed("Boiling some water")
  val preparingCoffee = ZIO.succeed("Preparing the coffee")

  val sequentialWakeUpRoutine =
    for {
      _ <- bathTime.debug(printThread)
      _ <- boilingWater.debug(printThread)
      _ <- preparingCoffee.debug(printThread)
    } yield ()

  val parallelWakeupRoutine =
    for {
      bath <- bathTime.debug(printThread).fork
      boil <- boilingWater.debug(printThread).fork
      _    <- bath.join
      _    <- boil.join
      _    <- preparingCoffee.debug(printThread)
    } yield ()

  val run = {
//    sequentialWakeUpRoutine.exitCode
    parallelWakeupRoutine.exitCode
  }
}

/**
 * //      zipped = bath.zip(boil)
//      result <- zipped.join.debug(printThread)
 */
