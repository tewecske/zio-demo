package com.pipl.zio.layers

import zio._

import scala.io.AnsiColor._

trait UserEmailer {
  def notify(user: User, message: String): Task[Unit]
}

final case class UserEmailerLive() extends UserEmailer {
  def notify(user: User, message: String): Task[Unit] =
    Task(println(s"${GREEN}${user.name}${RESET} ${message}"))
}

object UserEmailer extends Accessible[UserEmailer] {
  val live = ZLayer(ZIO.succeed(UserEmailerLive()))
//  val live = ZLayer.fromService[Any, UserEmailer](_ => UserEmailerLive())
}
