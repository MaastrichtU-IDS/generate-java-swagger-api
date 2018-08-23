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

import com.github.tminglei.swagger.SharingHolder;
import io.swagger.models.HttpMethod;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.util.Arrays;

import static com.github.tminglei.bind.Mappings.longv;
import static com.github.tminglei.bind.Mappings.*;
import static com.github.tminglei.swagger.SwaggerContext.param;
import static com.github.tminglei.swagger.SwaggerContext.response;
import static com.github.tminglei.swagger.SwaggerContext.sharing;
import static com.github.tminglei.swagger.SwaggerContext.*;
import static com.github.tminglei.bind.Simple.*;
import static com.github.tminglei.bind.Constraints.*;
import static com.github.tminglei.bind.Processors.*;

@Path("/sparql")
@Produces({"application/json", "application/xml"})
public class SparqlResource {


    /**
     * To describe the object returned by the API
     */
    static Mapping<?> sparqlMapping = $(mapping(
            field("id", $(longv()).desc("pet id").example(3).$$),
            field("name", $(text(required())).desc("pet name").$$),
            field("category", $(mapping(
                    field("id", longv(required())),
                    field("name", text(required()))
            )).desc("category belonged to").$$),
            field("photoUrls", $(list(text())).desc("pet's photo urls").example(Arrays.asList("http://example.com/photo1")).$$),
            field("tags", $(list(text())).desc("tags for the pet").example(Arrays.asList("tag1", "tag2")).$$)
    )).refName("SparqlRef").desc("Sparql info").$$;


    static SharingHolder sharing = sharing().pathPrefix("/sparql").tag("sparql");

    static {
        sharing.operation(HttpMethod.GET, "/:query<[0-9]+>")
                .summary("A valid SPARQL query.")
                .parameter(param(longv()).in("path").name("query").example(1l))
                .response(200, response(sparqlMapping))
                .response(404, response().description("sparql not found"))
        ;
    }
    @GET
    @Path("/{query}")
    public Response getSparql(@PathParam("query") String query) {
        return Response.ok().entity("<xml>Success</xml>").build();
    }

}
