package underscore.monads

import cats.data.State
import cats.syntax.applicative._

/**
  * @author Arun Manivannan
  */
object PostfixCalculatorState extends App {

  type CalcState[A] = State[List[Int], A]

  def operator(fn: (Int,Int) => Int): CalcState[Int] ={
    State[List[Int], Int] {
      case a :: b :: tail =>
        val answer = fn (a,b)
        (answer ::tail, answer) //Add answer to the first element to enable further calculation. Update the state as well

      case _ => sys.error("Single value found in List for supported binary operands")
    }
  }

  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" => operator (_ + _)
    case "-" => operator (_ - _)
    case "*" => operator (_ * _)
    case "/" => operator (_ / _)
  }


  //Damn !!
  def evalAll(input:List[String]): CalcState[Int] ={
    input.foldLeft(0.pure[CalcState]){(accumCal, str) =>
      accumCal.flatMap(_ => evalOne(str))
    }
  }

  def evalInput(input: String):Int = evalAll(input.split (" ").toList).runA(Nil).value



}
