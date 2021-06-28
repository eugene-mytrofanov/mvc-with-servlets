# MVC Servlet and Rest Servlet example

## How to run the app
1. Install Tomcat locally and run it
2. Build the project with: mvn clean install
3. Deploy the project with plugin: mvn tomcat7:deploy (mvn tomcat7:undeploy)

## How to test the app
1. Hello Form Servlet: http://localhost:8080/mvc-with-servlets/helloForm?first_name=Name&last_name=Surname
2. Student REST servlet:
   
    2.1 Create student - POST  http://localhost:8080/mvc-with-servlets/students
   
        body: {"firstName":"Mick","lastName":"Jagger","yearOfBirth":2001 }
    2.2 Get all students - GET  http://localhost:8080/mvc-with-servlets/students
   
   Get a single student - GET  http://localhost:8080/mvc-with-servlets/students?id=1

## References
1. https://www.baeldung.com/mvc-servlet-jsp
2. Maven tomcat plugin tutorial: https://www.youtube.com/watch?v=thEk-i2OIK4