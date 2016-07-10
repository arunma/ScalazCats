import scalaz._
import Scalaz._


sealed trait Kilogram

def Kilogram [A](a:A):A @@ Kilogram=Tag[A, Kilogram](a)

val mass = Kilogram(20.0)

20 * Tag.unwrap(mass)


sealed trait JoulePerKilogram


def JoulePerKilogram[A](a:A):A @@ JoulePerKilogram=Tag[A, JoulePerKilogram](a)

def energyR(m:Double @@ Kilogram) : Double @@ JoulePerKilogram=
 JoulePerKilogram(299792458.0 * 299792458.0 * Tag.unwrap(m))

energyR(Kilogram(10))

energyR(mass)