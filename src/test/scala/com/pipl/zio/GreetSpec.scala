package com.pipl.zio

import zio.test.Assertion._
import zio.Random
import zio.test._

object GreetSpec extends ZIOSpecDefault {
  override def spec: ZSpec[_root_.zio.test.TestEnvironment, Any] = suite("GreeterSpec") (
    test("should greet user") {
      val app = for {
        _    <- TestConsole.feedLines("Matan")
        name <- Greet.greetingZio
      } yield {
        name
      }

      assertM(app)(equalTo("Matan"))
    },

    test("should test random") {
      for {
        _  <- TestRandom.feedInts(1, 4, 8, 16)
        r1 <- Random.nextInt
        r2 <- Random.nextInt
        r3 <- Random.nextInt
      } yield
        assert(List(r1,r2,r3))(equalTo(List[Int]( elems =
          1, 4, 8,
        )))
    }
  )
}
