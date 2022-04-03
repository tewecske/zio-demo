package com.pipl.zio.services

object Model {
  case class UserId(id: Int)
  case class UserProfile(id: UserId, name: String)
}
