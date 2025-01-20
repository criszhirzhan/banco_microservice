# Fase de construcción: usar una imagen con OpenJDK y Maven
FROM maven:3.8.4-openjdk-17-slim AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar todo el código fuente y compilar el proyecto
COPY src ./src
RUN mvn clean package -DskipTests

# Añadir un paso para verificar que el archivo JAR se ha generado
RUN ls -la /app/target/

# Fase de ejecución: usar solo OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado desde la etapa anterior
COPY --from=build /app/target/banco-clientes-api-0.0.1-SNAPSHOT.jar /app/banco-clientes-api.jar

# Exponer el puerto en el que la aplicación Spring Boot se ejecutará
EXPOSE 8080

# Ejecutar el JAR de la aplicación
ENTRYPOINT ["java", "-jar", "/app/banco-clientes-api.jar"]
