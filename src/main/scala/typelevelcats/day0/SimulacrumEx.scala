package typelevelcats.day0
import simulacrum._

/**
  * @author Arun Manivannan
  */
object SimulacrumEx {

  @typeclass trait MonoidX[A]{
    @op("|+|") def mappend (a:A, b:A) : A
    def mzero :A
  }


  object MonoidX{
    val syntax = ops
    implicit val IntAddMonoidX: MonoidX[Int] = new MonoidX[Int] {
      override def mappend(a: Int, b: Int): Int = a+b
      override def mzero: Int = 0
    }
  }

  def main(args: Array[String]): Unit = {
    import MonoidX.syntax._

    print (1 |+| 10)
  }
}
