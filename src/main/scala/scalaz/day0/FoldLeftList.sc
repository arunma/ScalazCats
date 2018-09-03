
trait Monoid[A] {
  def mzero: A
  def mappend(a: A, b: A): A
}

object Monoid {


  implicit val IntMonoid: Monoid[Int] = new Monoid[Int] {
    def mappend(a: Int, b: Int): Int = a + b
    def mzero: Int = 0
  }

  implicit val StringMonoid: Monoid[String] = new Monoid[String] {
    def mappend(a: String, b: String) = a + b
    def mzero = ""
  }

}

object FoldLeftList {
  def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
}


def sum[A: Monoid](xs: List[A]): A = {
  val m = implicitly[Monoid[A]]
  xs.foldLeft(m.mzero)((x, y) => m.mappend(x, y))
}
/*
def sum[A: Monoid](xs: List[A]): A = {
  val m = implicitly[Monoid[A]]
  FoldLeftList.foldLeft(xs, m.mzero, m.mappend)
}*/

sum(List(1, 2, 3, 4))

sum(List("hello", "world", "bye"))


val s1 = Set (1,2,3)
val s2 = Set (3,4,5)

s1.diff(s2)

(s1.union(s2)) -- (s1.intersect(s2))