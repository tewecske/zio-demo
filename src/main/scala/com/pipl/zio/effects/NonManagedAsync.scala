package com.pipl.zio.effects

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object NonManagedAsync extends App {
  def three() = Future {
    println("I'm 3")
    3
  }


  println("====== result1: ")

  val result1 = for {
    a <- three()
    b <- three()
  } yield {
    println( (a, b) == (3,3) )
  }





  Await.result(result1, 3.seconds)

  println("====== result2: ")

  val f = three()

  val result2 = for {
    a <- f
    b <- f
  } yield {
    println( (a, b) == (3,3) )
  }
}
