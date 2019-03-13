FROM rainlf/ubuntu-java-python-locust

WORKDIR /home

COPY locust-files locust-files
COPY locust-runner-1.0.0.jar app.jar
COPY application.properties application.properties

EXPOSE 8088

VOLUME ["/home"]

ENTRYPOINT ["nohup", "java", "-jar", "app.jar", "--spring.config.location=application.properties", "-Djava.security.egd=file:/dev/./urandom", "&"]