package com.pipl.zio.layers

import zio._
import scala.io.AnsiColor._

trait UserDB {
  def insert(user: User): Task[Unit]
}

final case class UserDbLive() extends UserDB {
  def insert(user: User): Task[Unit] =
    Task(println(s"${GREEN}Inserting user ${user.name} into the database${RESET}"))
}

object UserDB extends Accessible[UserDB] {
  val live = ZLayer(ZIO.succeed(UserDbLive()))

/*  def insert(user: User): ZIO[UserDB, Throwable, Unit] =
    ZIO.serviceWithZIO[UserDB](_.insert(user))*/
}
