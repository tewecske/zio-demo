package com.pipl.zio.effects

object NonManaged extends App {
  val x = 3
  val y = x + x
  val z = 3 + 3

  require(y == z)




  def three() = {
    println("I'm 3")
    3
  }

  require ( (three(), three()) == (3, 3) )
}
