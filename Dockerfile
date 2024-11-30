FROM openjdk:23-jdk-slim
ARG JAR_FILE=target/core-0.0.1-SNAPSHOT.jar
# Usar la variable correctamente en COPY
COPY ${JAR_FILE} app.jar

# Exponer el puerto en el que corre la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
