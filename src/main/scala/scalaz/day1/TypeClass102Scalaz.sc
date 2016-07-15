trait CanTruthy[A]{
  def truthys(a:A):Boolean
}

object CanTruthy{
  implicit val intTruthy=new CanTruthy[Int] {
    override def truthys(a: Int): Boolean = a match {
      case 0=>false
      case 1=> true
    }
  }
}

//import scala.language.implicitConversions

trait ToCanTruthyOps[A]{
  def self:A
  implicit def F:CanTruthy[A]
  def truthy:Boolean=F.truthys(self)
}


object ToCanTruthyOps{
  implicit def toCanTruthys[A](a:A)(implicit ev:CanTruthy[A])=new ToCanTruthyOps[A] {
    override def self: A = a
    override implicit def F: CanTruthy[A] = ev
  }
}


import ToCanTruthyOps._

1.truthy

