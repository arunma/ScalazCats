import cats.data.EitherT

import scala.concurrent.Future
import cats.instances.future._
import cats.syntax.flatMap._
import cats.syntax.applicative._

val powerLevels = Map(
  "Jazz" -> 6,
  "Bumblebee" -> 8,
  "Hot Rod" -> 10
)

import scala.concurrent.ExecutionContext.Implicits.global

type Response[A] = EitherT[Future, String, A]
def getPowerLevel(autobot: String): Response[Int] = {
  powerLevels.get(autobot) match {
    case Some(avg) =>
      EitherT.right(Future(avg))
      //avg.pure[Response]
    case None => EitherT.left(Future(s"$autobot unreachable"))
  }
}


def canSpecialMove(ally1: String, ally2: String): Response[Boolean] = {
  for {
    power1 <- getPowerLevel(ally1)
    power2 <- getPowerLevel(ally2)
  } yield (power1 + power2) > 15
}


