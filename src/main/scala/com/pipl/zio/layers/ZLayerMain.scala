package com.pipl.zio.layers

import zio._

// https://blog.rockthejvm.com/structuring-services-with-zio-zlayer/

object ZLayerMain extends zio.App {
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    val userRegistrationLayer = (UserDB.live ++ UserEmailer.live) >>> UserSubscription.live

    UserSubscription.subscribe(User("matan.keidar", "matan.keidar@pipl.com"))
      .provideLayer(userRegistrationLayer)
      .catchAll(t => ZIO.succeed(t.printStackTrace()).map(_ => ExitCode.failure))
      .map { u =>
        println(s"Registered user: $u")
        ExitCode.success
      }
  }
}
