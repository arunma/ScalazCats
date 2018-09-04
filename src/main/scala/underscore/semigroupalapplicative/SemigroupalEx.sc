import cats.Semigroupal
import cats.instances.option._
import cats.syntax.apply._
import cats.instances.future._
import scala.concurrent.duration._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}

Semigroupal[Option].product(Some(10), Some("abc"))

Semigroupal.map3(Option(10), Option(11), Option(12))((a,b,c)=>a+b+c)

case class Cat (name:String, born:Int, color:String)


(
  Option("garfield"), Option(1978), Option("orange")
).mapN(Cat.apply)


val futurePair = Semigroupal[Future].product(Future("Hello"), Future(100))

Await.result(futurePair, 1.second)