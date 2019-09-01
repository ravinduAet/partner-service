#Rest endpoint for Partner onboard and retrieval

##partner-service Endpoints

Create Partner   - **POST** ***http://localhost:5001/partner-service/api/v1/partners***
Retrieve Partner - **GET**  ***http://localhost:5001/partner-service/api/v1/partners***
Retrieve Partner by Partner Type - **GET**  ***http://localhost:5001/partner-service/api/v1/partners?partner-type={partner-type}***

###Features

- Support API documentation
- Integration and transformation test cases
- MySQL databases interation with Spring JPA and Spring Data
- CORS configuration
- logging fuctionality
- Database Auditing
- Added a postman script to test endpoints. You can find it in static filder inside resource folder.
- Used Java 8 features and Spring boot 2.
- Tomcat server configurations.
- Used Gradle as building tool.

###Best practices followed.

- Followed Single responsibility principle
- Coding to interfaces 
- Open for extension closed for modification (Support scalble, extensible services which suport future enhancement)
- Applied Liskov substitution principle
- Used design patterns much as possible for suitable scenarios (Strategy - A Behaviour pattern).
- Managed simplicity of the project (Code readability)
- Necessary code comments.

###Enhancements for partner-service rest endpoint 

- We can maintain domain based exceptions while we are resolving complex domain problems (Example- Maintain a custom exception like PartnerException to mapped all partner related
exceptions)
- Introduce an acuator endpoints to monitor service metrics.
- introduce timeouts to reduce number of calling rounds to an external service. (Circuit breaker pattern)
- Introduce Api Gateway as a facade, that client need to integrate first with api-gateway and then redirect to the each rest services.
- I didnt implemented an authentication mechanism to this solution. Therefore we can introduce security token mechanism to api-gateway and from there, navigate to each individual services like partner-service.
- We can introduce a seperate attribute called status that can mapped response status inside response. For now i have send each status code in http headers ( Success, Failure, invalid attributes code and messages, etc. to get a clear
image you can refer postman collection)

  
