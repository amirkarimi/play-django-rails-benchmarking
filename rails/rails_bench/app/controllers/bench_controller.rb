class BenchController < ApplicationController
  def index
    rand = Random.rand(100)
    Person.create(name: "test" + rand.to_s, age: rand)
    @person = Person.first
    @person.destroy
  end
end
