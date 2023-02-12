# java-challenge
- This Project will provide the APIs to cteate,update,get employees,get emplyee and delete the employee details by using spring-boot.

### How to use this spring-boot project
- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.

#### What i Did
- Created EmployeeDTO to use it as a request object since to Exposing of entities creates a strong coupling between API and persistence model
- Created BaseResModel and EmployeeResModel which is extended by BaseResModel to return the response to Avoid exposing Employee Entity Model directly and to customize the API Response.
- Implemented Junt Test cases usink Mockito Framework.
- Used Spring Security Basic Authenication.
- Used Spring Bootâ€™s Bean Validation to Validate the input request and handelled the bean validations .
- Used Spring Boot in memory cache for database calls.

#### My thoughts to Implement Further
- Currently i used Spring Securirty Basic authentication, i though of using OAuth2 Authentication due to time constraint i couldnt do that
- I had an experience of using golden file testing for unit test, it is a best way to compare the entire respone.


#### My experience in Java

- I have 8+ years of experience in Development, Implementation & maintenance of web-based and client-server applications using JAVA, J2EE by using Spring and Spring Boot
- Have 2 + years of experience in Spring Boot