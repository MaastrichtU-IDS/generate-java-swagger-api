#!/bin/bash

docker run -v /data/jetty:/var/lib/jetty/webapps -p 8080:8080 generate-java-swagger-api
