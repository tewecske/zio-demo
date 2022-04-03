//package com.pipl.zio.services
//
//import com.pipl.zio.services.Model._
//import zio._
//
//object Database {
//  trait Service {
//    def lookup(id: UserId): Task[UserProfile]
//    def update(id: UserId, profile: UserProfile): Task[Unit]
//  }
//
//  val live = new Service {
//    def lookup(id: UserId): Task[UserProfile] =
//      Task.succeed(UserProfile(id, "John Doe") )
//
//    def update(id: UserId, profile: UserProfile): Task[Unit] =
//      console.putStrLn(s"updated $id") *> Task.succeed(())
//  }
//}
//
//trait Database {
//  def database: Database.Service
//}
//
//object db {
//  def lookup(id: UserId): RIO[Database, UserProfile] =
//    ZIO.accessM(_.database.lookup(id))
//
//  def update(id: UserId, profile: UserProfile): RIO[Database, Unit] =
//    ZIO.accessM(_.database.update(id, profile))
//}
