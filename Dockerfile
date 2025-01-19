# Usar una imagen base de OpenJDK 17 (ajustada a tu versión de Java)
FROM openjdk:17-jdk-slim

# Etiquetas de mantenimiento
LABEL maintainer="tu-email@dominio.com"
LABEL description="Microservicio Spring Boot para gestión de clientes bancarios"

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado desde el directorio target (ajusta el nombre si es necesario)
COPY target/banco-clientes-api.jar app.jar

# Exponer el puerto 8080 para que sea accesible desde fuera del contenedor
EXPOSE 8080

# Comando para ejecutar el archivo JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]