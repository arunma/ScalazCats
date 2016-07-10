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

trait MonoidOp[A]{
  val F:Monoid[A]
  val value:A
  def |+| (a2:A)= F.mappend(value, a2)
}

implicit def toMonoidOp[A:Monoid](a:A):MonoidOp[A] = new MonoidOp[A] {
  override val value: A = a
  override val F: Monoid[A] = implicitly[Monoid[A]]
}

3 |+| 4
