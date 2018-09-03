import cats._
import cats.implicits._

1===1

//1 === "hello"

1 > 2.0

1 tryCompare 2

3.show

"hello".show

(new {}).toString


import simulacrum._


@typeclass trait CanTruthy[A]{self =>
  def truthy(a:A):Boolean
}

object CanTruthy{
  def fromTruthy[A](f:A=>Boolean):CanTruthy[A]=new CanTruthy[A] {
    override def truthy(a: A): Boolean = f(a)
  }
}

implicit def intCanTruthy:CanTruthy[Int]=CanTruthy.fromTruthy({
  case 0=>false
  case _=>true
})

import CanTruthy.ops._

10.truthy






