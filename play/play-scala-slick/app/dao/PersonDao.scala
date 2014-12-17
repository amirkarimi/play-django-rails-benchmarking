package dao

import scala.slick.driver.PostgresDriver.simple._
import models._

object PersonDao {
  
  lazy val database = Database.forURL("jdbc:postgresql://localhost:5432/play-slick-bench", 
                          driver = "org.postgresql.Driver",
                          user = "postgres",
                          password = "123456")

  class Persons(tag: Tag) extends Table[Person](tag, "persons") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def age = column[Int]("age")

    def * = (id.?, name, age) <> (Person.tupled, Person.unapply _)
  }
  
  lazy val persons = TableQuery[Persons]

  def dropAndCreateTable(implicit session: Session) = {
    try {
        persons.ddl.drop
    } catch {
        case e =>
    }
    persons.ddl.create
  }

  def insert(person: Person)(implicit session: Session) = {
    persons.insert(person)
  }
  
  def delete(person: Person)(implicit session: Session) = {
    persons.filter(_.id === person.id).delete
  }

  def findFirst(implicit session: Session) = {
    persons.firstOption
  }
}

