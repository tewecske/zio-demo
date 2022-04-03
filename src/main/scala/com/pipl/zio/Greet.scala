package com.pipl.zio

import zio.{ExitCode, URIO}
import zio.console._


object Greet extends zio.App {
  val greetingZio =
    for {
      _    <- putStrLn("Hi! What is your name?")
      name <- getStrLn
      _    <- putStrLn(s"Hello, $name, welcome to Rock the JVM!")
    } yield name

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    greetingZio.exitCode
}
