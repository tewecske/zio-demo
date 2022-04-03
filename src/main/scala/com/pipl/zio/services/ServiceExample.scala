//package com.pipl.zio.services
//import com.pipl.zio.services.Model.{UserId, UserProfile}
//import zio._
//
//object ServiceExample extends zio.App {
//  val userId = UserId(1)
//
//  val lookedupProfile: RIO[Database, UserProfile] =
//    for {
//      profile <- db.lookup(userId)
//    } yield profile
//
//  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
//    lookedupProfile.provide()
//  }
//}
