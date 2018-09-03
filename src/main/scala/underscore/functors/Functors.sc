import cats._
import cats.instances.list._
import cats.instances.option._
import cats.syntax.functor._

import scala.concurrent.{ExecutionContext, Future}
import scala.language.higherKinds


val list1 = List(1, 2, 3)

val list2 = Functor[List].map(list1)(x => x + 1)

def fn(x: Int) = x * 3

//Lifted Function
val liftedFn = Functor[List].lift(fn)

val liftedOptFn = Functor[Option].lift(fn)


liftedFn(list1)

liftedOptFn(Option(24))


val func1 = (a: Int) => a + 1
val func2 = (a: Int) => a * 3
val func3 = (a: Int) => a + "!"


//val func4 = func1.map(func2).map(func3)
//func4(123)


def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]) = {
  start.map(n => n + 21)
}


doMath(Option(21))
doMath(List(1, 2, 3))

implicit val optionFunctor: Functor[Option] = new Functor[Option] {
  override def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa.map(f)
}

//Functor implementations accepting implicit parameters
implicit def futureFunctor(implicit ec: ExecutionContext): Functor[Future] = new Functor[Future] {
  override def map[A, B](fa: Future[A])(f: A => B): Future[B] = fa.map(f)
}


list1
  .map(_ * 3)
  .map(_ * 5)
  .map(_.toString)