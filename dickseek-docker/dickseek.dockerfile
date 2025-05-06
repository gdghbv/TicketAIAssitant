FROM openjdk:17
ENV TZ=Asia/Guangzhou
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY dickseek-1.1.jar /app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
