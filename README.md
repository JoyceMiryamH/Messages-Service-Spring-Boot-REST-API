# Messages-Service-Spring-Boot-REST-API

### Project Details
This application manages messages and provides details about them. Specifically, users may find out whether a given message is a palindrome or not. The application supports the following operations:
* Create, retrieve, update, and delete a message.
* Retrieve a list of all messages.


### Architecture
This application was build in Java with the use of the Spring Boot framework following a RESTful design pattern. It can be broken down into three distinct layers; the model, the controller and the service. The application is connected to a MySQL database, using Spring Data JPA to access the database.

##### REST API
**CREATE MESSAGE**

*POST /messages/create*
- Path Variable(s): NA
- Request Body: The message text. 
* Return: The id of the message if it's successfully created.

**RETRIEVE MESSAGE**

*GET /messages/{messageId}*
- Path Variable(s): The id of the message the user would like to retrieve.
- Request Body: NA
* Return: The message if found, else, a MessageNotFoundException is thrown.

**UPDATE MESSAGE**

*PUT /messages/update/{messageId}*
- Path Variable(s): The id of the message the user would like to update.
- Request Body: The updated message text.
* Return: The updated version of the message (with the new text, new last modified time and date, etc.)

**DELETE MESSAGE**

*DELETE /messages/delete/{messageId}*
- Path Variable(s): The id of the message the user would like to delete.
- Request Body: NA
* Return Value(s): A message indicating whether the message was successfully deleted or not.

**RETRIEVE ALL MESSAGES**

*GET /messages/all*
- Path Variable(s): NA
- Request Body: NA
* Return Value(s): A list of all the messages stored in the database. 

**CHECK MESSAGE IS A PALINDROME**

*GET /messages/check-palindrome/{messageId}*
- Path Variable(s): The id of the message the user would like to check.
- Request Body: NA
* Return Value(s): A boolean representing whether or not the message's text is a palindrome. 

### Running this application
#### build
Build the application by running `mvn clean install`. 

#### running locally
A local deployment of the application can be found at 
> http://localhost:8080/.

A swagger interface is also available to execute the HTTP endpoints. Use the following deployment 
> http://localhost:8080/swagger-ui.html.

#### Deploying and running with docker
$ docker build -t springio/gs-spring-boot-docker .
$ docker run -p 8080:8080 springio/gs-spring-boot-docker


### Future improvements
* This application could benefit from the hidding of the primary key of the database by creating some intermittant layer which generates a unique message id for the users.
* As well, in future implementations, users could benefit from a bulk adding functionality where they could create more than one message at a time.
* Finally, users could also greatly benefit from some sort of querying functionality where they could search for message with certains characteristics (e.g. creation date, modification date, etc.)
