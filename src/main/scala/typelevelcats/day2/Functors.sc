import cats._
import cats.implicits._
import simulacrum.typeclass

@typeclass trait Functor1[F[_]] extends functor.Invariant[F]{ self =>

  def map[A,B](fa:F[A])(f:A=>B):F[B]

  def lift[A,B](f:A=>B):F[A]=>F[B]= map (_)(f) //fa => map(fa)(f)

  def product[A,B](fa:F[A])(f:A=>B):F[(A,B)]=map (fa)(a=>a->f(a))

}

val lifted=Functor[List].lift{(a:Int)=>a*3}

lifted(List(1,2,3))



Functor[List].fproduct(List(1,2,3))(_*3)


//1. Identity rule.
/*
From FP in Scala
Implementations satisfying this law are restricted from doing strange things like
 throwing exceptions, removing the first element of a List, converting a Some to None etc
  */

Functor[List].map(List(1,2,3))(identity)


val x:Either[String,Int]=Right(1)

x map identity

x map (_*2)

val y:Either[String,Int]=Left("Hello")

y map (_*2)

y map (_+"bye")

//2. Composition rule

/*
The second law states that composing two functions and then mapping the resulting function
over a Functor should be the same as first mapping one function over the functor and then
mapping the other one

 */


val f={(_:Int)*3}
val g={(_:Int)+1}

val h=f map g

assert {(x map (f map g)) === (x map f map g)}


