package com.pipl.zio.effects

import zio._

object Managed extends zio.App {
  def three() = {
    println("I'm 3")
    3
  }

  val task = ZIO.effect( three() )


  val result = for {
    a <- task
    b <- task
  } yield (a,b)

  println(task, task)


  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    result.exitCode
}