package com.pipl.zio.layers

import zio._

trait UserSubscription {
  def subscribe(user: User): Task[User]
}

final case class UserSubscriptionLive(notifier: UserEmailer, userModel: UserDB) extends UserSubscription {
  override def subscribe(user: User): Task[User] =
    for {
      _ <- userModel.insert(user)
      _ <- notifier.notify(user, s"Welcome ${user.name} to ZIO demo")
    } yield user
}

object UserSubscription extends Accessible[UserSubscription] {
  val live = ZLayer{
    for {
      notifier <- ZIO.service[UserEmailer]
      userModel <- ZIO.service[UserDB]
    } yield UserSubscriptionLive(notifier, userModel)
  }

/*  def subscribe(user: User) =
    ZIO.serviceWithZIO[UserSubscription](_.subscribe(user))*/
}
