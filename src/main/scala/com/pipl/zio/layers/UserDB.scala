package com.pipl.zio.layers

import zio._
import scala.io.AnsiColor._

object UserDB {
  type UserDbEnv = Has[UserDB.Service]

  trait Service {
    def insert(user: User): Task[Unit]
  }


  val live: ZLayer[Any, Nothing, UserDbEnv] = ZLayer.succeed(new Service {
    override def insert(user: User): Task[Unit] =
      Task {
        println(s"${YELLOW}[Database]${RESET} insert into public.user values (${user.name})")
      }
  })

  // front facing API
  def insert(user: User): ZIO[UserDbEnv, Throwable, Unit] =
    ZIO.accessM(_.get.insert(user))
}
