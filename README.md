# generate-java-swagger-api

## Overview
This is a Java project to build a stand-alone server generated from a Swagger Java object which implements the Swagger spec.  You can find out more about both the spec and the framework at http://swagger.io.

### To run (with Maven)
To run the server, run this task:
```shell
mvn jetty:run-war
```

This will start Jetty embedded on port 8080.

Access it at http://localhost:8080/

### Run with Docker

```shell
docker build -t generate-java-swagger-api .

docker run -p 8080:8080 generate-java-swagger-api
```



### Testing the server
Once started, you can navigate to http://localhost:8080/api/swagger.json to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.

### Using the UI
There is an HTML5-based API tool bundled in this sample, you can view it it at http://localhost:8080. This lets you inspect the API using an interactive UI.  You can access the source of this code from [here](https://github.com/swagger-api/swagger-ui)

You can then open the dist/index.html file in any HTML5-enabled browser.  Upen opening, enter the URL of your server in the top-centered input box (default is http://localhost:8080/api/swagger.json).  Click the "Explore" button and you should see the resources available on the server.

### Applying an API key
The sample app has an implementation of the Swagger ApiAuthorizationFilter.  This restricts access to resources based on api-key.  There are two keys defined in the sample app:

* default-key
* special-key

When no key is applied, the "default-key" is applied to all operations.  If the "special-key" is entered, a
number of other resources are shown in the UI, including sample CRUD operations.



Project built from https://github.com/tminglei/binder-swagger-java/tree/master/example/java-jaxrs