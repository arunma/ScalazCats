import simulacrum._

@simulacrum.typeclass trait CanAppend[A]{
  @op("|+|") def append(a1:A, a2:A):A
}

implicit val intCanAppend:CanAppend[Int]=new CanAppend[Int] {
  override def append(a1: Int, a2: Int): Int = a1+a2
}

//import CanAppend.ops._

//1 |+| 4 //Does not work over a worksheet

