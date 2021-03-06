trait CanTruthy[A] {
  self =>
  def truthys(a: A): Boolean
}

object CanTruthy {
  def truthys[A](f: A => Boolean): CanTruthy[A] = new CanTruthy[A] {
    override def truthys(a: A): Boolean = f(a)
  }
}


implicit val intCanTruthy: CanTruthy[Int] = CanTruthy.truthys({
  case 0 => false
  case _ => true
})


trait CanTruthyOps[A] {
  def self: A
  implicit def F: CanTruthy[A]
  final def truthy: Boolean = F.truthys(self)
}


object ToCanTruthyOps {

  implicit def toCanIsTruthyOps[A](v:A)(implicit ev:CanTruthy[A])=new CanTruthyOps[A] {
    override def self: A = v
    override implicit def F: CanTruthy[A] = ev
  }

}

import ToCanTruthyOps._

1.truthy

0.truthy



