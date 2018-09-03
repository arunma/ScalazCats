import cats.data.{Reader, Writer}
import cats.implicits._

object WriterReaderMonads extends App {

  type Logged[A] = Writer[Vector[String], A]
  123.pure[Logged]

  val writer = for {
    a <- 10.pure[Logged]
    _ <- Vector("a", "b", "c").tell
    b <- 32.writer(Vector("x", "y", "z"))
  } yield a + b

  val (vect, resInt) = writer.run

  println(writer)
  println(vect)
  println(resInt)


  //Reader

  case class Cat(name: String, favFood: String)

  val greetKitty: Reader[Cat, String] = Reader(cat => s"Hello ${cat.name}")
  val feedKitty: Reader[Cat, String] = Reader(cat => s"Have a nice bowl of ${cat.favFood}")

  val greetAndFeed: Reader[Cat, String] = for {
    greet <- greetKitty
    feed <- feedKitty
  } yield s"$greet.$feed"

  println(greetAndFeed(Cat("Garfield", "lasagne")))


  case class Db(
                 usernames: Map[Int, String],
                 passwords: Map[String, String]
               )

  type DbReader[A] = Reader[Db, A]

  def findUserName(userId: Int): DbReader[Option[String]] = Reader(db => db.usernames.get(userId))

  def checkPassword(
                     username: String,
                     password: String
                   ): DbReader[Boolean] = Reader(db => db.passwords.get(username).contains(password))


  //Wow.
  def checkLogin(
                  userId: Int,
                  password: String
                ): DbReader[Boolean] = {
    for {
      username <- findUserName(userId)
      passwordOk <- username.map { usern =>
        checkPassword(usern, password)
      }.getOrElse {
        false.pure[DbReader]
      }
    } yield passwordOk
  }
}


