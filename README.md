## Spring Boot CRUD WebApp

### About the app
 This is web application with an ability to fetch and modify database records. Developed using Spring Boot and Java, it has two data objects as the entities.
 The two entities are User and Occupation. The User entity has a foreign key column pointing to occupation id. The database script file and the database design image are included among the project files as Database script and ER Diagram.png respectively.

 ### REPL and APIs
 Among the project folders is a database script(Databse script) that when executed generates the database. The app is interactive through REPL mode and web APIs.

#### REPL edpoints include:
 list users
 get user 1(an example of an id value)
 delete user 1(an example of an id value)

 list occupation
 get occupation 1(an example of an id value)
 delete occupation 1(an example of an id value)

 
### The wep edpoints include:
#### User end points
list all users
 GET:http://localhost:8080/app/users

Add users
 POST:http://localhost:8080/)app/users

get a user
 GET:http://localhost:8080/app/users/{id}

 list all users by occupation
 GET:http://localhost:8080/app/users/{title}

 delete a user
 DELETE:http://localhost:8080/app/users/{id}

 update a user
 PUT:http://localhost:8080/app/users/{id}

 #### occupation end points
list all occupations
 GET:http://localhost:8080/app/users

Add an occupation
 POST:http://localhost:8080/)app/occupation

get an occupation
 GET:http://localhost:8080/app/occupation/{id}

 delete an occupation
 DELETE:http://localhost:8080/app/occupation/{id}

 update an occupation
 PUT:http://localhost:8080/app/occupation/{id}
 

 


