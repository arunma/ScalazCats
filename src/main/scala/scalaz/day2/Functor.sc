/*trait Functor[F[_]] {
  self =>
  def map[A, B](fa: F[A])(f: A => B): F[B]
}*/

import scalaz._
import Scalaz._

Functor[List].lift((a: Int) => a * 2)

val liftedOption = Functor[Option].lift((_: Int) * 2)

liftedOption(Some(5))




//Lift
val listLift=Functor[List].lift((a:Int)=>a*5)

listLift(List(3,4,5))

