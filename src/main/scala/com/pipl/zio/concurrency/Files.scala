package com.pipl.zio.concurrency

import zio._

object Files extends ZIOAppDefault {
  def printThread = s"[${Thread.currentThread().getName}]"

  def openFile(id: Int) =
    if (id > 10) {
      ZIO.fail(s"I'm failed on $id")
    } else {
      ZIO.succeed( Thread.sleep(1000) )
    }

  def createTask(id: Int) = {
    for {
      _ <- Console.printLine(s"${printThread} Start $id")
      _ <- openFile(id).ensuring(ZIO.succeed(println(s"finalizing ${id}")) )
      _ <- Console.printLine(s"${printThread} End $id")
    } yield id
  }

  val tasks = 1 to 100 map createTask

  val app = ZIO.foreachPar(tasks)(identity).withParallelism(20)

  val run = {
    app.exitCode
  }
}
