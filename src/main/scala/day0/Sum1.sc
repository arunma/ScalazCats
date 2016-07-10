def sum(xs: List[Int]): Int = {
  xs.foldLeft(0)(_ + _)
}

sum(List(1, 2, 3))


trait Monoid[A] {
  def mzero: A

  def mappend(a: A, b: A): A
}


object IntMonoid extends Monoid[Int] {
  def mappend(a: Int, b: Int): Int = a + b

  def mzero: Int = 0
}


def sum2(xs: List[Int]): Int = xs.foldLeft(IntMonoid.mzero)(IntMonoid.mappend)



sum2(List(1, 2, 3))

def sum3[A](xs: List[A], m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)

sum3(List(1, 2, 3), IntMonoid)


def sum4[A: Monoid](xs: List[A]) = {
  val m = implicitly[Monoid[A]]
  xs.foldLeft(m.mzero)(m.mappend)
}

