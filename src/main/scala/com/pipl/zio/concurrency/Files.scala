package com.pipl.zio.concurrency

import zio._

object Files extends zio.App {
  def printThread = s"[${Thread.currentThread().getName}]"

  def openFile(id: Int) =
    if (id > 10) {
      ZIO.fail(s"I'm failed on $id")
    } else {
      ZIO.effectTotal( Thread.sleep(1000) )
    }

  def createTask(id: Int) = {
    for {
      _ <- console.putStrLn(s"${printThread} Start $id")
      _ <- openFile(id).ensuring(ZIO.effectTotal(println(s"finalizing ${id}")) )
      _ <- console.putStrLn(s"${printThread} End $id")
    } yield id
  }

  val tasks = 1 to 100 map createTask

  val app = ZIO.foreachParN(20)(tasks)(identity)

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    app.exitCode
  }
}
