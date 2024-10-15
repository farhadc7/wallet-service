FROM maven:3.9.6-eclipse-temurin-17-alpine as build

ENV HOME=/wallet
RUN mkdir -p $HOME
WORKDIR $HOME

ENV TZ=Asia/Tehran
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY pom.xml .
COPY src/main/java ./src/main/java

#COPY target/wallet-service-0.0.1-SNAPSHOT.jar .

RUN mvn  clean install
COPY src/main/resources ./src/main/resources
COPY src/test/ ./src/test

FROM eclipse-temurin:17-jre-alpine

ARG APPLICATION_XML_LOG
ARG APPLICATION_NAME
ARG APPLICATION_PROPERITES

ENV HOME=/wallet
RUN mkdir -p $HOME
WORKDIR $HOME
RUN mkdir $HOME/logs

ENV APPLICATION_NAME=${APPLICATION_NAME:-wallet-service-0.0.1-SNAPSHOT.jar}
ENV APPLICATION_PROPERITES=${APPLICATION_PROPERITES:-application.properties}
ENV APPLICATION_XML_LOG=${APPLICATION_XML_LOG:-logback.xml}

COPY --from=build /wallet/target/wallet-service-0.0.1-SNAPSHOT.jar app.jar
COPY --from=build /wallet/src/main/resources/application-live.properties application.properties
COPY --from=build /wallet/src/main/resources/logback.xml .

EXPOSE 80

ENTRYPOINT java -Dlogging.confing='/wallet/logback.xml' -jar /wallet/app.jar --spring.config.location=/wallet/application.properties


