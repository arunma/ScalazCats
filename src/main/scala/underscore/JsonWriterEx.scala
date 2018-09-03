package underscore

/**
  * @author Arun Manivannan
  */

sealed trait Json

final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
case object JsNull extends Json

trait JsonWriter[A] {
  def write(value: A): Json
}

case class Person (name: String, email: String)

object JsonWriterInstances {
  implicit val stringWriter = new JsonWriter[String] {
    override def write(value: String): Json = JsString(value)
  }
  implicit val personWriter = new JsonWriter[Person] {
    override def write(value: Person): Json = JsObject(Map(
      "name" -> JsString(value.name),
      "email" -> JsString(value.email)
    ))
  }
  implicit def optionWriter[A](implicit writer :JsonWriter[A])  = new JsonWriter[Option[A]] {
    override def write(value: Option[A]): Json = value match {
      case Some (inner) => writer.write(inner)
      case None => JsNull
    }
  }
}

object JsonSyntax {
  implicit class JsonWriterOps[A](value:A) {
    def toJson(implicit w: JsonWriter[A]) = w.write(value)
  }
}

object JsonWriterEx {

  def main(args: Array[String]): Unit = {
    import JsonWriterInstances._
    import JsonSyntax._
    println (Person("arun", "arun@arunma.com").toJson)
    println (Option("arun").toJson(optionWriter))
    println (Option(Person("arun", "arun@arunma.com")).toJson)
  }
}



