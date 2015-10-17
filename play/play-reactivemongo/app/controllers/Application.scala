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
    collection.find(BSONDocument()).cursor[BSONDocument].collect[List]() map { foundPerson =>
      Ok(foundPerson.map(p => BSONDocument.pretty(p)).mkString(","))
    } recover { case _ =>
      Ok("Faild")
    }
    
  }
  
  def hello = Action {
    Ok("Hello World")
  }

}
