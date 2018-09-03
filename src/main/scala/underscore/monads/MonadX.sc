import cats._
import cats.instances.list._
import cats.instances.option._
import cats.syntax.flatMap._
import cats.syntax.functor._
import cats.Id

import scala.language.higherKinds


trait MonadS[F[_]] {
  def pure[A](a: A): F[A]

  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

  def map[A, B](value: F[A])(func: A => B): F[B] = flatMap(value)(a => pure(func(a)))
}


Monad[Option].flatMap(Option(10))(o => Option(o * 20))

Monad[List].flatMap(List(1, 2, 3))(each => List(each - 1, each + 1))



def sumSquare[F[_]:Monad](a: F[Int], b:F[Int]):F[Int]={
  //a.flatMap(x => b.map(y => x*x + y*y))

  for {
    x <- a
    y <- b
  } yield x*x + y*y
}

sumSquare(List(1,2,3), List(3,4,5))

//sumSquare(1, 3) - Cannot do without Id

sumSquare(1:Id[Int], 2:Id[Int])


Monad[Id].flatMap(10:Id[Int])(x => x *10)


trait IdX{
  def pure[A](value: A): Id[A] = value

  def map[A, B](initial: Id[A])(func: A => B): Id[B] = func(initial)

  def flatMap[A, B](initial: Id[A])(func: A => Id[B]): Id[B] = func(initial)
}