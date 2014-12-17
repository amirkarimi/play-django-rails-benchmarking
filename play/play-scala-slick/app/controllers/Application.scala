package controllers

import play.api._
import play.api.mvc._
import dao._
import scala.slick.driver.PostgresDriver.simple._
import scala.util.Random
import models._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Application extends Controller {

  def index = Action {
    PersonDao.database.withSession { implicit session =>
        val rand = new Random().nextInt(100)
        PersonDao.insert(Person(None, "test" + rand.toString, rand))
        val person = PersonDao.findFirst
        
        person map { p =>
            PersonDao.delete(p)
        }        

        Ok(views.html.index(person))
    }
  }

}
