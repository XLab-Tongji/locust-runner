FROM rainlf/ubuntu-java-python-locust

MAINTAINER ashwwf@outlook.com

WORKDIR /home

ADD scenarios scenarios
ADD target/locust-runner-1.0.0.jar app.jar
ADD src/main/resources/application.properties  application.properties

EXPOSE 8088

ENV PGW="127.0.0.1:9091"

VOLUME ["/home"]

ENTRYPOINT ["java", "-jar", "app.jar", "--pushgateway=${PGW}", "--spring.config.location=application.properties", "-Djava.security.egd=file:/dev/./urandom"]

