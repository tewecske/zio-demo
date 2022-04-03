package com.pipl.zio.effects

import zio._

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import zio.console._

object ManagedAsync extends zio.App {
  def three() = Future {
    println("I'm 3")
    3
  }

  val result1 = for {
    _ <- console.putStrLn("====== result1: ")
    a <- ZIO.fromFuture( global => three() )
    b <- ZIO.fromFuture( global => three() )
  } yield {
    println( (a, b) == (3,3) )
  }




  val task = ZIO.fromFuture( global => three() )

  val result2 = for {
    _ <- console.putStrLn("====== result2: ")
    a <- task
    b <- task
  } yield {
    println( (a,b) == (3,3) )
  }

  val result = result1 zip result2

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    result.exitCode
}