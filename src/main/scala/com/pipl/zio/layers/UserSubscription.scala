package com.pipl.zio.layers

import zio._

object UserSubscription {
  import UserEmailer._
  import UserDB._

  type UserSubscriptionEnv = Has[UserSubscription.Service]

  class Service(notifier: UserEmailer.Service, userModel: UserDB.Service) {
    def subscribe(user: User): Task[User] =
      for {
        _ <- userModel.insert(user)
        _ <- notifier.notify(user, s"Welcome ${user.name} to ZIO demo")
      } yield user
  }

  val live: ZLayer[UserEmailerEnv with UserDbEnv, Nothing, UserSubscriptionEnv] =
    ZLayer.fromServices[UserEmailer.Service, UserDB.Service, UserSubscription.Service] {
      (emailer, db) => new Service(emailer, db)
    }

  // accessor
  def subscribe(user: User): ZIO[UserSubscriptionEnv, Throwable, User] = ZIO.accessM(_.get.subscribe(user))
}
