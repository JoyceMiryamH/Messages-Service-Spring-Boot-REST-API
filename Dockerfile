FROM amazoncorretto:8

RUN yum update -y --security

# Set app user home directory
RUN yum install -y shadow-utils &&\
    mkdir /home/nobody && \
    chown nobody:nobody /home/nobody && \
    usermod -d /home/nobody nobody && \
    yum remove -y shadow-utils

WORKDIR /usr/app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

USER nobody

CMD java -server -jar app.jar