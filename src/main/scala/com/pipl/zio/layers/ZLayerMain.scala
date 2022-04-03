package com.pipl.zio.layers

import zio._

// https://blog.rockthejvm.com/structuring-services-with-zio-zlayer/

object ZLayerMain extends ZIOAppDefault {
  val run = {
    UserSubscription(_.subscribe(User("matan.keidar", "matan.keidar@pipl.com")))
      .catchAll(t => ZIO.succeed(t.printStackTrace()).map(_ => ExitCode.failure))
      .map { u =>
        println(s"Registered user: $u")
        ExitCode.success
      }
      .provide(UserDB.live, UserEmailer.live, UserSubscription.live)
  }
}
