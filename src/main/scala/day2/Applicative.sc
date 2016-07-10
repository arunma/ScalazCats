import scalaz.Scalaz._
import scalaz._


val curriedFn = List(1, 2, 3, 4) map {
  (_: Int) * (_: Int)
}.curried

curriedFn.map { each => each(9) }

1.point[List]

1.point[Option]

1.point[Option] map {
  _ + 2
}

1.point[List] map {
  _ * 3
}



/*trait Apply[F[_]] extends Functor[F]{self =>
  def ap[A,B](fa: =>F[A])(f: =>F[A=>B]):F[B]
}*/

9.some <*> { a: Int => a * 3 }.some

List(1, 2, 3) <*> {
  List(
    (x:Int)=>x*3,
    (x:Int)=>x*100
  )
}





