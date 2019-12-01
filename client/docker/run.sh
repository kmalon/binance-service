#!/bin/sh

java ${JAVA_OPTS} -Dspring.profiles.active=$PROFILE -jar /usr/local/microservice/SERVICE_JAR_NAME.jar