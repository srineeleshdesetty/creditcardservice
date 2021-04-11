# creditcardservice
A Spring boot based microservices application which exposes RESTful api for credit card services . 

## Getting Started 
* Make sure you have Java 8 installed.
* Make sure you have Maven
* If running outside a docker container Make sure application.properties is available to the application.  

##  Build
```
run mvn clean install
```
## Docker
* To build docker image run ```docker build -t mybank/creditcardservice .``` 
* To run docker container run ``` docker run -p 8080:8080  mybank/creditcardservice```

## Swagger url
* Enable swagger by setting the following application.properties to true
```creditcardservice.swagger.config=true```
* Swagger url http://localhost:8080/creditcardservice/swagger-ui.html

## Starting the application in IDE
just right click on Application.Java and run as java application. If you want to start it with a specific profile use -Dspring.profiles.active={env}

## Common issues at startup

* Do not see any logs. Check the spring profile you are using. For some profiles we don't log to the console.

## Logging

Find all the log settings in logback.xml
Logs are written to creditcardservice.log file. Log files are in stored in a folder called logs. It is relative to the project root folder. Archived logs are added to archived folder.


## HealthCheck
```
https://localhost:8080/creditcardservice/actuator/health
```

