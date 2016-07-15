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
  FoldLeftList.foldLeft(xs, m.mzero, m.mappend)
}

sum(List(1, 2, 3, 4))

sum(List("hello", "world", "bye"))



