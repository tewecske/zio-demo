package com.pipl.zio.effects

import zio._

object Managed extends ZIOAppDefault {
  def three() = {
    println("I'm 3")
    3
  }

  val task = ZIO.attempt( three() )


  val result = for {
    a <- task
    b <- task
  } yield (a,b)

  println(task, task)


  val run =
    result.exitCode
}