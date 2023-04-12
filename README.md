# Paytm Spring Boot Boilerplate

[![N|Solid](https://bitbucket.org/paytmteam/paytm-spring-boot-boilerplate/raw/613aa69c41596c42845007089557dfe57e7a18c0/company-web/src/main/resources/readme-assets/paytm-logo.png)](https://paytm.com)

This is the boilerplate spring boot code from Paytm. Unlike other boilerplates, this code has almost all features which should be in an enterprise microservice

  - Multi-module Springboot with JPA.
  - Spring profiling- dev, stag, and prod
  - SOLID design for writing RESTful APIs using Processor Model.
  - Request and response logging of the API.
  - Rest client boilerplate
  - AWS S3 integration
  - Custom Rest Request Wrapper for request and response multi-reading.
  - Guava in-memory cache.
  - Gson Serializer.
  - Custom threadpool for contextsafe multi-threading.
  - Generic bean validator, and other utils like AssertionUtil, custom threadpool autowiring, UUID Utils etc.
  - Support for distributed tracing like mechanism in logging using logback using request-id and span-id concepts.
  - Custom Rest Advice implementation to catch exceptions like bad request, excpetion from controller, unknown API, and return standard response instead of the default HTML one.
  - State Transition Tables, Scheduler, and Services boilerplate.
  - Runtime fetching and update of configs.

## How to use this in code for writing a new microservice

  - Download the codebase. Do not clone.
  - The directory and module stucture of this code follows the com.company.* model. Change it to whatever you want. 
  - Change the name of the parent and sub-module folders and other repace other occurrences of the work "company" with whatever you want.
  - Build, run and enjoy.

## Code setup

This code requires-
  - [Java 8](https://www.java.com/en/download/)
  - [Intelllij](https://www.jetbrains.com/idea/download/) IDE
  - Maven 3.5 or above

Build the codebase...

```sh
$ cd company-backend
$ mvn clean install -T4 -DskipTests
```
[![N|Solid](https://bitbucket.org/paytmteam/paytm-spring-boot-boilerplate/raw/613aa69c41596c42845007089557dfe57e7a18c0/company-web/src/main/resources/readme-assets/build.png)]()
Run the codebase...

```sh
$ sudo mkdir /etc/.files
$ java -Xms256M -Xmx512M -XX:PermSize=64m -XX:MaxPermSize=128m -XX:+UseParallelGC -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime -XX:+PrintHeapAtGC -Xloggc:/etc/.files/gc/gc.log -Dspring.profiles.active=prod -jar company-web-0.0.1-SNAPSHOT.jar --spring.config.location=/etc/.files/application-prod.properties
```
Note- The user with which the jar is running should have the permissions to write in the appropriate folders for log creation.

[![N|Solid](https://bitbucket.org/paytmteam/paytm-spring-boot-boilerplate/raw/613aa69c41596c42845007089557dfe57e7a18c0/company-web/src/main/resources/readme-assets/run.png)]()

## Sample APIs exposed CURL-

```sh
curl -X GET \
  http://localhost:9999/property \
  -H 'cache-control: no-cache' \
  -H 'secret: jfkgadsgbcshtrefda21ey89vs'
```

```sh
curl -X POST \
  http://localhost:9999/property \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"key":"profile",
	"value":"stag",
	"secret":"jfkgadsgbcshtrefda21ey89vs"
}'
```
