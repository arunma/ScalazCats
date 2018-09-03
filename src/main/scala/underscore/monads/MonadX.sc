import cats._
import cats.instances.list._
import cats.instances.option._
import cats.syntax.flatMap._
import cats.syntax.functor._
import cats.Id
import cats.syntax.applicative._
import cats.syntax.either._

import scala.language.higherKinds


trait MonadS[F[_]] {
  def pure[A](a: A): F[A]

  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

  def map[A, B](value: F[A])(func: A => B): F[B] = flatMap(value)(a => pure(func(a)))
}


Monad[Option].flatMap(Option(10))(o => Option(o * 20))

Monad[List].flatMap(List(1, 2, 3))(each => List(each - 1, each + 1))



def sumSquare[F[_] : Monad](a: F[Int], b: F[Int]): F[Int] = {
  //a.flatMap(x => b.map(y => x*x + y*y))

  for {
    x <- a
    y <- b
  } yield x * x + y * y
}

sumSquare(List(1, 2, 3), List(3, 4, 5))

//sumSquare(1, 3) - Cannot do without Id

sumSquare(1: Id[Int], 2: Id[Int])


Monad[Id].flatMap(10: Id[Int])(x => x * 10)


trait IdX {
  def pure[A](value: A): Id[A] = value

  def map[A, B](initial: Id[A])(func: A => B): Id[B] = func(initial)

  def flatMap[A, B](initial: Id[A])(func: A => Id[B]): Id[B] = func(initial)
}


print(1.pure[Option])

1.pure[List]


val either1: Either[String, Int] = Right(10)
val either2: Either[String, Int] = Right(32)

for {
  a <- either1
  b <- either2
} a + b



val a = 10.asRight[String]
val b = 32.asRight[String]

for {
  aa <- a
  bb <- b
} aa + bb

//adding only if list has just non-negative numbers
def countPositives(nums: List[Int]) = {
  nums.foldLeft(0.asRight[String]) { (acc, num) =>
    if (num > 0) {
      acc.map(_ + num)
    }
    else {
      Left("negative. stopping !")
    }
  }
}


"error".asLeft[Int].recover {
  case str: String => -1
}

"error".asLeft[Int].recoverWith {
  case str: String => Right(-1)
}


for {
  a <- 1.asRight[String]
  b <- 0.asRight[String]
  c <- if (b == 0) "DIV0".asLeft[Int]
  else (a / b).asRight[String]
} yield c


case class User(username: String, password: String)
sealed trait LoginError

case object UnexpectedError extends LoginError
case class UserNotFound(username:String) extends LoginError
case class PasswordIncorrect (username: String) extends LoginError

type LoginResult = Either[LoginError, User]

val result1:LoginResult = User("dave", "password").asRight

val result2:LoginResult = UserNotFound("user not found").asLeft

val y = Eval.always{
  println ("evaluating y")
  math.random()
}

y.value

y.value

1.pure