import scalaz._
import Scalaz._

Monad[Option].point(1)

9.some flatMap{x=> Monad[Option].point(x*10)}

