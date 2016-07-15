import scalaz._
import Scalaz._


List(1,2,3) |+| List(4,5,6)

val list1=List(1,2,3)
val list2=List(4,5,6)

list1 ++ list2
list1 |+| list2


list1.isMZero
val nilList:List[Int]=Nil
nilList.isMZero


Monoid[List[Int]].zero

Monoid[String].zero


