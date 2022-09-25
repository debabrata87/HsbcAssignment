# HSBC Multi Currency Payment App
HSBC Multi currency Payment application with command line interface

# High Level Requirement :

  Command Line Interface to display Multi Currency Balance and Accept Payment and display remaining balance after processing Payment.
  Programmes continue to run and display balance untill user input "quit" 

# GIT URL 
  
  -https://github.com/debabrata87/HsbcAssignment.git

# Tools Used for development 

  1- STS 
  
  2- JDK 1.8 
  
  3- Springboot

# Application Type:
  
  Spring boot Starter Project

# Build Tool:

  Maven 3.8.6

# Depenency :

   1- Spring boot starter depenpency 
   
   2- H2 
   
   3- JPA

# Data Setup and Confiuration :

  1- data.sql file on resource folder contains currency and the initial balance along with exchange rate  [ as in 22/09/2022 corresponding to USD ] which will be displayed as initial balance

# Build:

  Go to Project directory where pom.xml is present and run below command 
  
  mvn install 

# Run Application :
  
  Go to <Project Home>\target Directory and run the application by command 
  
  java -jar HsbcAssignment-0.0.1-SNAPSHOT.jar
  
# Known Issues:
