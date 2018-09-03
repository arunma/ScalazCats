package underscore.monads

import cats.Eval
import cats.data.Writer

object TrampoliningEval extends App {

  def factorial(n: BigInt): Eval[BigInt] = {
    if (n == 1) {
      Eval.now(n)
    }
    else {
      Eval.defer(factorial(n - 1).map(_ * n))
    }
  }

  print(factorial(500000))


  def foldRightEval[A, B](as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] =
    as match {
      case head :: tail =>
        fn(head, foldRight(tail, acc)(fn))
      case Nil =>
        acc
    }

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    foldRightEval(as, Eval.now(acc)){(a,b) => b.map(bb => fn(a,bb))}.value



}
