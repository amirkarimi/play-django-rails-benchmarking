## Target ##
I want to compare Django, Rails and Play Framework performance in the same situation.

## Conditions ##
All apps connect to a Postgres database (each have its own database) and do the following task in sequence:

- Insert a simple entity into the database (containing two fields, a string and an integer)
- Fetch the first entity from the respective table and keep it into the memory
- Delete the fetch entity from database (still available in the memory)
- Render a simple layout containg the fetched entity

All apps are running in their production environment:

- Rails is published to Apache (Passenger)
- Django is published to Apache (WSGI)
- Play is run using `start` command (which is recommended for production)

## Benchmarking ##
Benchmarking is done using `ab` (Apache Benchmark) using the following command:
```
ab -n 10000 -c 200 [url]
```

## Project Structure ##
Django app path: `~/django/django_bench` 
Rails app path: `~/rails/rails_bench` 
Play app path: `~/play/play-scala-slick` 

## Mongo DB ##
All the above apps are using a blocking library for data access. I wanted to check the impact of using async library for data access. I could implement the same conditions with Scala/Play/ReactiveMongo (As I know them better than the others).

Play/ReactiveMongo app is placed in: `~/play/play-reactivemongo`