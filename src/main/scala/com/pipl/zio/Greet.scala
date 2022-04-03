package com.pipl.zio

import zio.{ExitCode, URIO, ZIOAppDefault}
import zio.Console


object Greet extends ZIOAppDefault {
  val greetingZio =
    for {
      _    <- Console.printLine("Hi! What is your name?")
      name <- Console.readLine
      _    <- Console.printLine(s"Hello, $name, welcome to Rock the JVM!")
    } yield name

  val run =
    greetingZio.exitCode
}
