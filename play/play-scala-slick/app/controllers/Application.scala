package controllers

import play.api._
import play.api.mvc._
import dao._
import scala.util.Random
import models._
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object Application extends Controller {

  def setup = Action {
    PersonDao.dropAndCreateTable
    Ok("Ok")
  }

  def index = Action.async {
    PersonDao.findAll.map { persons =>
      Ok(persons.toString)
    }
  }

}
