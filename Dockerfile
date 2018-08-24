FROM jetty:9.4

LABEL maintainer "Vincent Emonet <vincent.emonet@maastrichtuniversity.nl>"

USER root

RUN apt-get update && apt-get install -y maven openjdk-8-jdk
COPY . .
RUN mvn clean install && \
    mv target/generate-java-swagger-api-0.0.1-SNAPSHOT.war /var/lib/jetty/webapps/root.war

USER jetty

# ADD ./target/generate-java-swagger-api-0.0.1-SNAPSHOT.war /var/lib/jetty/webapps/root.war
EXPOSE 8080
