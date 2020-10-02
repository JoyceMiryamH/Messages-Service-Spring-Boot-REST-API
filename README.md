# Messages-Service-Spring-Boot-REST-API

### Project Details
This application manages messages and provides details about them. Specifically, users may find out whether a given message is a palindrome or not. The application supports the following operations:
* Create, retrieve, update, and delete a message.
* Retrieve a list of all messages.


### Architecture
This application was build in Java with the use of the Spring Boot framework following a RESTful design pattern. It can be broken down into three distinct layers. The model, the controller and the service. The application is connected to a MySQL database, using Spring Data JPA to access the database.

### Running this application
#### build
Build the application by running `mvn clean install`. 

#### run
A local deployment of the application can be found at 
> http://localhost:8080/.

A swagger interface is also available to execute the HTTP endpoints. Use the following deployment 
> http://localhost:8080/swagger-ui.html.


### Future improvements
This application could benefit from the hidding of the primary key of the database by creating some intermittant layer which generates a unique message id for the users.
