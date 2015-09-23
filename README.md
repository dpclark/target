# Target.com case study / technical assesment
## Solution 1 - RESTful web service

The case study for myretail was to create a restful web service to query for product and price information. Additionally I created end points to query by category name as well. There were other supporting facts 
such as the size of the store as well as the number of products per store. Those facts didn't play into the POC. We were free to select the software stack. I chose the following stack:

* Tomcat for the servlet container - I used an embedded version in the poc.
* Spring Boot / Actuator
  - It uses annotations for the document to storage mapping
  - It provides runtime beans for interfaces when there is a simple implementation
  - Actuator provides serveral rest resources automatically that would be valudable in production such as /status
  - It has NoSQL integration using a similar format to it's jpa/jdbc data storage structure
* MongoDB
  - While the volume of data doesn't warrant a NoSQL solution the format of the entity does map well to a document format
  - Even though a relational model wasn't selected it can be injected easily as we would just update the entity and repository
* Gradle
  - It is a new technology to me and provides a lot of functionality
  - It is well integrated into the spring stack
  
 In structuring this poc using Spring I followed the Spring best practices in using 'src/main/java...' as well as package names such as domain, service and web to partition the application logic across
 these layers. See the tree output below for the full directory structure.

```
.
+-- build
¦   +-- classes
¦   ¦   +-- main
¦   ¦   ¦   +-- com
¦   ¦   ¦       +-- myretail
¦   ¦   ¦           +-- api
¦   ¦   ¦               +-- Application.class
¦   ¦   ¦               +-- domain
¦   ¦   ¦               ¦   +-- Product.class
¦   ¦   ¦               ¦   +-- ProductRepository.class
¦   ¦   ¦               +-- MongoConfig.class
¦   ¦   ¦               +-- service
¦   ¦   ¦               ¦   +-- ProductService.class
¦   ¦   ¦               ¦   +-- ProductServiceImpl.class
¦   ¦   ¦               +-- web
¦   ¦   ¦                   +-- ProductController.class
¦   ¦   +-- test
¦   ¦       +-- com
¦   ¦           +-- myretail
¦   ¦               +-- api
¦   ¦                   +-- AbstractTest.class
¦   ¦                   +-- domain
¦   ¦                   ¦   +-- ProductRepositoryTest.class
¦   ¦                   +-- service
¦   ¦                       +-- ProductServiceTest.class
¦   +-- dependency-cache
¦   +-- distributions
¦   ¦   +-- solution1.tar
¦   ¦   +-- solution1.zip
¦   +-- libs
¦   ¦   +-- myretail-api-1.0.0.jar
¦   ¦   +-- myretail-api-1.0.0.jar.original
¦   +-- reports
¦   ¦   +-- tests
¦   ¦       +-- classes
¦   ¦       ¦   +-- com.myretail.api.domain.ProductRepositoryTest.html
¦   ¦       ¦   +-- com.myretail.api.service.ProductServiceTest.html
¦   ¦       +-- css
¦   ¦       ¦   +-- base-style.css
¦   ¦       ¦   +-- style.css
¦   ¦       +-- index.html
¦   ¦       +-- js
¦   ¦       ¦   +-- report.js
¦   ¦       +-- packages
 +-- com.myretail.api.domain.html
¦   ¦           +-- com.myretail.api.service.html
¦   +-- resources
¦   ¦   +-- main
¦   ¦       +-- application.properties
¦   +-- scripts
¦   ¦   +-- solution1
¦   ¦   +-- solution1.bat
¦   +-- test-results
¦   ¦   +-- binary
¦   ¦   ¦   +-- test
¦   ¦   ¦       +-- output.bin
¦   ¦   ¦       +-- output.bin.idx
¦   ¦   ¦       +-- results.bin
¦   ¦   +-- TEST-com.myretail.api.domain.ProductRepositoryTest.xml
¦   ¦   +-- TEST-com.myretail.api.service.ProductServiceTest.xml
¦   +-- tmp
¦       +-- compileJava
¦       ¦   +-- emptySourcePathRef
¦       +-- compileTestJava
¦       ¦   +-- emptySourcePathRef
¦       +-- jar
¦           +-- MANIFEST.MF
+-- build.gradle
+-- gradle
¦   +-- wrapper
¦       +-- gradle-wrapper.jar
¦       +-- gradle-wrapper.properties
+-- gradlew
+-- gradlew.bat
+-- src
¦   +-- main
¦   ¦   +-- java
¦   ¦   ¦   +-- com
¦   ¦   ¦       +-- myretail
¦   ¦   ¦           +-- api
¦   ¦   ¦               +-- Application.java
¦   ¦   ¦               +-- domain
¦   ¦   ¦               ¦   +-- Product.java
¦   ¦   ¦               ¦   +-- ProductRepository.java
¦   ¦   ¦               +-- MongoConfig.java
¦   ¦   ¦               +-- service
¦   ¦   ¦               ¦   +-- ProductServiceImpl.java
¦   ¦   ¦               ¦   +-- ProductService.java
¦   ¦   ¦               +-- web
¦   ¦   ¦                   +-- ProductController.java
¦   ¦   +-- resources
¦   ¦   ¦   +-- application.properties
¦   ¦   +-- webapp
¦   ¦       +-- WEB-INF
¦   +-- test
¦       +-- java
¦       ¦   +-- com
¦       ¦       +-- myretail
¦       ¦           +-- api
¦       ¦               +-- AbstractTest.java
¦       ¦               +-- domain
¦       ¦               ¦   +-- ProductRepositoryTest.java
¦       ¦               ¦   +-- ProductRepositoryTest.java.save
¦       ¦               +-- service
¦       ¦               ¦   +-- ProductServiceTest.java
¦       ¦               +-- web
¦       +-- resources
```

In architecting for the web I choose the following URI convention

* /api/products - the findAll resource. Should produce a list of all products
* /api/products/{id} - the findByProductId resource. It will query the list of products by the path variable.
* /api/products/category?name={name} - the findByCategory resource. It will query the list of products by the category field

By placing api as the root of the path I leave open the extension of the POC to include future resources such as stores and categories. 

## Relevant Code / Classes

* Spring Actuator
  - automatically wires up monitoring resources
    - localhost:8080/health produces {"status":"up"}
  - provides enterprise features such as audit and metric monitoring (not leveraged in the poc)
* src/main/java/com/myretail/api/domain/ProductRepository.java
  - by extending the existing MongoRepository interface and adding a few additional methods Spring provides a robust
  - leverages method naming convention findBy<fieldname><modifiers> to allow for east definition of query methods
  - if this convention isn't flexible enough you can expose the template via a bean annotation in the MongoConfig class
  
```java 
public interface ProductRepository extends MongoRepository<Product, String> {
        /**
         * return a list of products filter by the category
         * @param category - the name of the category to filter the product list with
         * @return List<Product>
         * @see ProductService#findByCategory
        */
        List<Product> findByCategory(String category);
        /**
        * return a specified instance of product using the extern id field
        * @param productId - the external id field of the product
        * @return Product
        * @see ProductService#findByProductId
        */
        Product findByProductId(Long productId);
}
```

* src/main/java/com/myretail/api/service/ProductServer(Impl).java
  - this interface/class pair is a layer of inderection between the controller and the repository
  - we can keep this same interface and change the repository without the controller being aware of the change
* src/main/java/com/myretail/api/web/ProductController.java
  - maps the REST end points to the service layer.
  - for the poc I've restricted the request verbs to GETs so no object creation or modification

The domain class is annoated entity that tells Spring what collection to store it in as well as which fields are ids/indexed. We could have
added a data transfer pojo but the POC didn't warrant that layer. The Application class uses the SpringBootApplication annotation which is a wrapper for three other annotations:
- ComponentScan
- EnableAutoConfiguration
- Configuration
It reduces clutter. The MongConfig class extends the abstract mongo config class to specify a default database name. We can leverage runtime settings in the future to change this variable. It also launches
a mongo db factory and template and exposes them via the bean annotation.

## Summary
By leveraging gradle, SpringBoot, and SpringBootActuator I was able to quickly build a working prototype to query product information from a NoSQL database. In this case MongoDB. The Spring runtime's
ability to make logically guesses about interfaces and create beans on the fly provides a powerful way to quickly wire up a project without a lot of boilerplate code. The SpringBootAcutator project provides several REST resources
that are valuable for enterprise ready applications. Being that this is a POC we didn't take advantage of most of them. Additionally we only used plain json as our response type. We didn't leverage Spring's HATEOS projects that provide
REST discovery mechanisms as part of the response. Extending this POC to provide HATEOS as the content type as well as finishing exposing the other CRUD methods is a trivial task. 
Choosing the right combination of software made this POC come together quickly as well as it provides a solid base if we were to move forward on developing a REST API for myretail's online grocery store.
