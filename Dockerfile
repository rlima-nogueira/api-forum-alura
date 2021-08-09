#Criando o container com o java
FROM openjdk:11
#adicionando um usuario spring, dentro do grupo spring
RUN addgroup -S spring && adduser -S spring -G spring -G spring
#utilizando o usuario criado
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [ "java", "Main", "-Xmx512m", "-Dserver.port=${PORT}", "-jar", "/app.jar" ]
