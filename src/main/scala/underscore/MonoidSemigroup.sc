import cats._
import cats.implicits._

implicit def setUnionMonoid[A] = new Monoid[Set[A]] {
  override def empty = Set.empty[A]

  override def combine(x: Set[A], y: Set[A]) = x.union(y)
}

implicit def setIntersectSemigroup[A] = new Semigroup[Set[A]] {
  override def combine(x: Set[A], y: Set[A]) = x.intersect(y)
}


def add[A: Monoid](items: List[A]): A = {
  items.foldLeft(Monoid[A].empty)(_ |+| _)
}


def addInt(items: List[Int]): Int = {
  items.foldLeft(Monoid[Int].empty)(_ |+| _)
}

def addOptInt(items: List[Option[Int]]): Option[Int] = {
  items.foldLeft(Monoid.empty[Option[Int]])(_ |+| _)
}




addOptInt(List(Some(1), Some(2), Some(5)))

case class Order(totalCost: Double, quantity: Double)

implicit val monoid = new Monoid[Order]{
  override def empty = Order(0,0)
  override def combine(x: Order, y: Order) = Order(
    x.totalCost + y.totalCost,
    x.quantity + y.quantity
  )
}


add(List(Order(1,2), Order(10,11)))


val map1 = Map("a" -> 1, "b" -> 2)
val map2 = Map("b" -> 3, "d" -> 4)

map1 |+| map2