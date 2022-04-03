package com.pipl.zio

import zio.test.Assertion._
import zio.test.environment._
import zio.duration._
import zio.random
import zio.test._

object GreetSpec extends DefaultRunnableSpec {
  override def spec: ZSpec[_root_.zio.test.environment.TestEnvironment, Any] = suite("GreeterSpec") (
    testM("should greet user") {
      val app = for {
        _    <- TestConsole.feedLines("Matan")
        name <- Greet.greetingZio
      } yield {
        name
      }

      assertM(app)(equalTo("Matan"))
    },

    testM("should test random") {
      for {
        _  <- TestRandom.feedInts(1, 4, 8, 16)
        r1 <- random.nextInt
        r2 <- random.nextInt
        r3 <- random.nextInt
      } yield
        assert(List(r1,r2,r3))(equalTo(List[Int]( elems =
          1, 4, 8,
        )))
    }
  )
}
