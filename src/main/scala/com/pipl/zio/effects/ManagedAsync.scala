package com.pipl.zio.effects

import zio._

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global

object ManagedAsync extends ZIOAppDefault {
  def three() = Future {
    println("I'm 3")
    3
  }

  val result1 = for {
    _ <- Console.printLine("====== result1: ")
    a <- ZIO.fromFuture( global => three() )
    b <- ZIO.fromFuture( global => three() )
  } yield {
    println( (a, b) == (3,3) )
  }




  val task = ZIO.fromFuture( global => three() )

  val result2 = for {
    _ <- Console.printLine("====== result2: ")
    a <- task
    b <- task
  } yield {
    println( (a,b) == (3,3) )
  }

  val result = result1 zip result2

  val run =
    result.exitCode
}