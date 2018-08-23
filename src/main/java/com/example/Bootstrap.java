/**
 *  Copyright 2015 SmartBear Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.example.data.H2DB;
import io.swagger.models.auth.In;

import static com.github.tminglei.bind.Mappings.longv;
import static com.github.tminglei.swagger.SwaggerContext.*;
import static io.swagger.models.HttpMethod.GET;

import com.github.tminglei.swagger.SharingHolder;

public class Bootstrap extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static {  // for swagger
    swagger().info(info()
              .title("Bio2RDF")
              .description("Bio2RDF is an open source project to generate and provide linked data for the life sciences.")
              .termsOfService("https://github.com/bio2rdf/bio2rdf-scripts/wiki/Terms-of-use")
              .contact(contact().name("Michel Dumontier").email("michel.dumontier@gmail.com"))
              .license(license().name("MIT License")
                              .url("https://github.com/bio2rdf/bio2rdf-scripts/blob/master/MIT-LICENSE.txt")
              )
      ).host("localhost:8002")
      .basePath("/api")
      .consumes("application/json").consumes("application/xml")
      .produces("application/json").produces("application/xml")
      .securityDefinition("api_key", apiKeyAuth("api_key", In.HEADER))
      .securityDefinition("petstore_auth", oAuth2()
                      .implicit("http://petstore.swagger.io/api/oauth/dialog")
                      .scope("read:pets", "read your pets")
                      .scope("write:pets", "modify pets in your account")
      ).tag(tag("pet").description("Everything about your Pets")
                      .externalDocs(externalDocs().description("Find out more").url("http://swagger.io"))
      ).tag(tag("store").description("Access to Petstore orders")
      ).tag(tag("user").description("Operations about user")
              .externalDocs(externalDocs().description("Find out more about our store").url("http://swagger.io"))
      );
  }

    static SharingHolder sharing = sharing().pathPrefix("/sparql").tag("sparql");

    static {
        sharing.operation(GET, "/:sparql<[0-9]+>")
                .summary("A valid SPARQL query.")
                .parameter(param(longv()).in("path").name("query").example(1l))
                .response(200, response().description("sparql found!"))
                .response(404, response().description("sparql not found"))
        ;
    }

  @Override
  public void init(ServletConfig config) throws ServletException {
      //H2DB.setupDatabase();
  }


/*
  public static void main(String[] args) {
    OpenAPI api = generateApi();

    System.out.println(api);
  }

  private static OpenAPI generateApi() {
    OpenAPI api = new OpenAPI();
    api.setInfo(generateInfo());
    api.setServers(generateServers());
    api.setPaths(generatePaths());

    return api;
  }

  private static Paths generatePaths() {
    Paths paths = new Paths();

    // here we have the Info from our Database extracted and we generate one set of
    // Paths for each Object
    paths.addPathItem("getItem", generatePathFor("getItem"));
    paths.addPathItem("sparql", generatePathFor("query"));

    return paths;
  }

  private static PathItem generatePathFor(String objectName) {
    PathItem pathItem = new PathItem();
    switch (objectName) {
      case "getItem":
        pathItem.addParametersItem(generateParameterItem("prefix", "path", "a valid prefix (see http://prefixcommons.org)"));
        pathItem.addParametersItem(generateParameterItem("identifier", "path", "a valid identifier for the resource"));
        break;
      case "query":
        pathItem.addParametersItem(generateParameterItem("query", "query", "A valid SPARQL query."));
        break;
      default:
        throw new RuntimeException("I don't know the Object \"" + objectName + "\"");
    }

    return pathItem;
  }

  private static Parameter generateParameterItem(String name, String in, String description) {
    Parameter parameter = new Parameter();
    parameter.setName(name);
    parameter.setIn(in);
    parameter.setDescription(description);
    parameter.setSchema(getSchemaFor(name));

    return parameter;

  }

  private static Schema getSchemaFor(String name) {
    Schema schema = new Schema();

    switch (name) {
      case "prefix":
      case "identifier":
      case "query":
        schema.setType(name);
        break;
      case "output":
        schema.setType(name);
        schema.addEnumItemObject((Object)"text/plain");
        schema.addEnumItemObject((Object)"text/html");
        schema.addEnumItemObject((Object)"application/rdf+xml");
      default:
        throw new RuntimeException("I don't know the Parameter \"" + name + "\"");
    }

    return schema;
  }

  private static List<Server> generateServers() {
    List<Server> servers = new ArrayList();

    servers.add(generateServer());

    return servers;
  }

  private static Server generateServer() {
    Server server = new Server();
    server.setUrl("http://bio2rdf.org");
    server.setDescription("Bio2RDF Production server");
    server.addExtension("x-location", "Maastricht, Netherlands");
    server.addExtension("x-maturity", "production");

    return server;
  }

  private static Info generateInfo() {
    Info info = new Info();
    info.setTitle("Bio2RDF");
    info.setDescription(
            "Bio2RDF is an open source project to generate and provide linked data for the life sciences.");
    info.setTermsOfService("https://github.com/bio2rdf/bio2rdf-scripts/wiki/Terms-of-use");

    info.setLicense(generateLicense());
    info.setContact(generateContact());

    info.setVersion("4.0.0");
    info.addExtension("x-implementationLanguage", "Java");
    info.addExtension("x-accessRestriction", "none");

    return info;
  }

  private static Contact generateContact() {
    Contact contact = new Contact();
    contact.setName("Michel Dumontier");
    contact.setEmail("michel.dumontier@gmail.com");

    contact.addExtension("x-role", "responsible-developer");
    contact.addExtension("x-id", "https://orcid.org/0000-0003-4727-9435");

    return contact;
  }

  private static License generateLicense() {
    License license = new License();
    license.setName("MIT License");
    license.setUrl("https://github.com/bio2rdf/bio2rdf-scripts/blob/master/MIT-LICENSE.txt");
    return license;
  }*/

}
