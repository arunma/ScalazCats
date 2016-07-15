
trait CanTruthy[A] {
  self =>
  def truthys(a: A): Boolean
}


object DefaultTruthyConversion {
  implicit def intCanTruthy: CanTruthy[Int] = new CanTruthy[Int] {
    override def truthys(a: Int): Boolean = a match {
      case 0 => false
      case _ => true
    }
  }
}

//Interface objects
object ToCanTruthySimpleOps {
  def truthy[A](a: A)(implicit ev: CanTruthy[A]): Boolean = {
    ev.truthys(a)
  }
}

//Implicit classes
object ToCanTruthyRefinedClassOps{
  implicit class toTruthysClass[A](value:A){
    def truthy(implicit ev:CanTruthy[A]): Boolean ={
      ev.truthys(value)
    }
  }
}

import DefaultTruthyConversion._

ToCanTruthySimpleOps.truthy(0)
ToCanTruthySimpleOps.truthy(1)


import ToCanTruthyRefinedClassOps._

0.truthy
1.truthy


