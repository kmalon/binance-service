FROM openjdk:8-jdk-alpine
ARG SERVICE_JAR_NAME
ENV JAR_DIR_IN_DOCKER=/usr/local/microservice/
RUN mkdir -p ${JAR_DIR_IN_DOCKER}
ADD ${SERVICE_JAR_NAME}.jar ${JAR_DIR_IN_DOCKER}
ADD run.sh run.sh
RUN chmod +x run.sh
RUN sed -i 's/SERVICE_JAR_NAME/'${SERVICE_JAR_NAME}'/' ./run.sh
RUN cat ./run.sh
CMD ./run.sh
