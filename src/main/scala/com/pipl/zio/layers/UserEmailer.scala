package com.pipl.zio.layers

import zio._

import scala.io.AnsiColor._

object UserEmailer {
  type UserEmailerEnv = Has[UserEmailer.Service]

  trait Service {
    def notify(user: User, message: String): Task[Unit]
  }


  val live: ZLayer[Any, Nothing, UserEmailerEnv] = ZLayer.succeed(new Service {
    override def notify(user: User, message: String): Task[Unit] =
      Task {
        println(s"${RED}[Email Service]${RESET} sending $message to ${user.email}")
      }
  })

  // front facing API
  def notify(user: User, msg: String): ZIO[UserEmailerEnv, Throwable, Unit] =
    ZIO.accessM(_.get.notify(user, msg))
}
