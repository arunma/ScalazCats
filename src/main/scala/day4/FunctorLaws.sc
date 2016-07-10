import scalaz._
import Scalaz._

//Identify
List(1,2,3) map (identity)

//Associativity
List(1, 2, 3) map {
  ((_: Int) * 3) map ((_: Int) + 1)
}

(List(1, 2, 3) map ((_: Int) * 3)) map ((_: Int) * 5)