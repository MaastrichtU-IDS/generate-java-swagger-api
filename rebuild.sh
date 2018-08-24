#!/bin/bash

mvn clean package

mkdir -p /data/jetty
cp target/generate-java-swagger-api-0.0.1-SNAPSHOT.war /data/jetty/root.war
