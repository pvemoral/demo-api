##Project

An easy API REST microservice application based on Spring-boot as a Java's Framework using the following applications:
- GitHub as a Git repository hosting service (https://github.com) 
- Gradle as a building tool. (https://gradle.org/)
- Spring Data JPA to store and retrieve data in a relational database.
- H2 a Java SQL in-memory databases (http://www.h2database.com)
- JaCoCo as a Java Code Coverage Library (Test code coverage - https://www.eclemma.org/jacoco)
- JUnit Spring-boot framework to testing.

The project is organized following the next structure:
```bash
-- src/main/java/com.api.demo
| -- config 
| -- controllers
| --  models
| -- repositories
| -- services
```    

```bash
-- src/test/java/com.api.demo
| -- controllers
| -- services
```
##Installation
```bash
git clone https://github.com/pvemoral/demo-api.git
```
## Get Started
Open as a Spring-boot project in your favorite IDE.

## Jacoco Report
Use the following sentence to check the Jacoco Test Report

```bash
./gradlew build jacocoTestReport
```