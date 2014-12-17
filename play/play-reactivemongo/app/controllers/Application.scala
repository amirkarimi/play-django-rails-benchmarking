package controllers

import play.api._
import play.api.mvc._
import reactivemongo.api._
import reactivemongo.bson._
import reactivemongo.bson.Macros.Annotations.Key
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util._

case class Person(@Key("_id") id: BSONObjectID, name: String, age: Int)

object Application extends Controller {

  // gets an instance of the driver
  // (creates an actor system)
  val driver = new MongoDriver
  val connection = driver.connection(List("localhost"))

  // Gets a reference to the database "plugin"
  val db = connection("play-reactivemongo")

  // Gets a reference to the collection "acoll"
  // By default, you get a BSONCollection.
  val collection = db("test")  

  val rand = new Random()
  implicit val documentHandler = Macros.handler[Person]

  def index = Action.async {
    val randInt = rand.nextInt
    val personName = "test" + randInt
    val personAge = randInt
    val id = BSONObjectID.generate
    
    collection.insert(Person(id, personName, personAge)) flatMap { _ =>
      collection.find(BSONDocument("_id" -> id)).one[Person] flatMap { foundPerson =>
        collection.remove(BSONDocument("_id" -> id)) map { _ =>
          Ok(views.html.index("Found Person"))
        }
      }
    } recover { case _ =>
      Ok("Faild")
    }
    
  }

}
