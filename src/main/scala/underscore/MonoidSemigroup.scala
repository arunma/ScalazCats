import cats._
import cats.implicits._



implicit def setUnionMonoid[A] = new Monoid[Set[A]] {
  override def empty = Set.empty[A]
  override def combine(x: Set[A], y: Set[A]) = x.union(y)
}

implicit def setIntersectSemigroup[A]=new Semigroup[Set[A]]{
  override def combine(x: Set[A], y: Set[A]) = x.intersect(y)
}

def add[A](items:List[A]): A={
  items.foldLeft(Monoid[A].empty)(_ |+| _)
}

def add (items:List[Int]):Int ={
  items.foldLeft(Monoid[Int].empty, |+|)
}