package dao

import models.Person
import slick.driver.PostgresDriver.api._

object PersonDao {
  
  lazy val db = Database.forURL("jdbc:postgresql://localhost:5432/play-slick-bench",
                          driver = "org.postgresql.Driver",
                          user = "postgres",
                          password = "123456",
                          keepAliveConnection = true)

  class Persons(tag: Tag) extends Table[Person](tag, "persons") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def age = column[Int]("age")

    def * = (id.?, name, age) <> (Person.tupled, Person.unapply _)
  }
  
  lazy val persons = TableQuery[Persons]

  def dropAndCreateTable = {
    try {
        persons.schema.drop
    } catch {
        case e: Throwable =>
    }
    persons.schema.create
  }

  def insert(person: Person) = {
    db.run(persons += (person))
  }
  
  def delete(person: Person) = {
    db.run(persons.filter(_.id === person.id).delete)
  }

  def findAll = {
    db.run(persons.result)
  }

  def findFirst = {
    db.run(persons.result.headOption)
  }
}

