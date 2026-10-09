# OTP in class assignment

# 1. Overview and Project Objectives

The project aimed to create a simple temperature converter app with jenkins, docker, maven and MariaDB integration.
The app converts temperatures from Celsius to Fahrenheit and vice versa and shows the conversion history.

# 2. Technologies and Dependencies
▪ Frontend () --> JavaFX 

▪ Backend () --> Java JDK 25

▪ Runtime () --> Maven

▪ Database () --> MariaDb

▪ Testing () --> JUnit 5

▪ Dependencies () --> JaCoCo, Jenkins, Mockito, MariaDB, Junit

# 3. Design and Development Methodology

The main program uses the MVC model. The whole application is divided into parts:
- The controller class that handles the user input and calls the conversion class to perform the conversion
- The view class that displays the result to the user
- The view was created using Scene Builder and CSS styling. To keep the application as simple as possible, only one page is used for the entire program.
- A simple database was used to store the conversion history.
- The conversion is saved as an Entity called conversionRecord, that has the result value, unit and time of conversion.

# 4. Functional Testing
## Unit testing
Unit test were implemented by using JUnit 5. Mockito was used to mock database and especially JavaFX components to prevent tests from opening GUI windows.
## Jenkins
Jenkins was integrated to for CI/CD. The pipeline: Git repository >> Jenkins >> Maven build >> JUnit tests >> JACOCO >> Docker image >> Docker Hub

# 5. Set-Up
### Requirements
- Java JDK 25
- Maven
- MariaDB

### Getting the application
- Clone repository
    - git clone <https://github.com/JanetteJK/OTP1_inclass_ass_Janette>
- Set up database using the sql script that can be found in Documents
- Build and run the project:
    - mvn clean install
- Launch the application from the Main class
- Run tests:
    - mvn test
- The Docker image could be found on Docker Hub if it worked but unfortunately I don't know what's wrong with it.
