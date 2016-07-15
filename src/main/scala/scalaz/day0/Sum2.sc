
def sum(xs: List[Int]): Int = {
  xs.foldLeft(0)(_ + _)
}

sum(List(1, 2, 3))


trait Monoid[A] {
  def mzero: A

  def mappend(a: A, b: A): A
}

object Monoid {


  implicit val IntMonoid:Monoid[Int]=new Monoid[Int]{
    def mappend(a: Int, b: Int): Int = a + b
    def mzero: Int = 0
  }

  implicit val StringMonoid:Monoid[String]=new Monoid[String]{
    def mappend(a:String, b:String)=a+b
    def mzero=""
  }

}


def sum4[A:Monoid](xs:List[A])={
  val m=implicitly[Monoid[A]]
  xs.foldLeft(m.mzero)(m.mappend)
}


sum4(List(1,2,3))

sum4(List("hello", "world", "bye"))