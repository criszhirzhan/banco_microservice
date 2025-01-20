# Banco Clientes Microservice

## Descripción

`banco-clientes` es una aplicación basada en microservicios desarrollada con Spring Boot para la gestión de clientes bancarios. Esta aplicación proporciona funcionalidades para administrar información de clientes de un banco utilizando Spring Data JPA para la gestión de datos y Spring Security para la protección de los recursos.

### Tecnologías utilizadas:
- **Spring Boot**: Para la creación y configuración del microservicio.
- **PostgreSQL**: Para la persistencia de los datos de los clientes bancarios.
- **Lombok**: Para simplificar el código y evitar la necesidad de escribir métodos `getters`, `setters`, y otros.
- **JPA (Java Persistence API)**: Para la interacción con la base de datos PostgreSQL.


---

## Despliegue con Docker

Para desplegar la aplicación en Docker, sigue los siguientes pasos:

### 1. **Construir la imagen de Docker de la aplicación**

Primero, asegúrate de estar en el directorio raíz del proyecto. Luego, ejecuta el siguiente comando para construir la imagen de Docker de la aplicación:

```bash
docker build -t banco-clientes-app .
```

### 2. **Ejecutar el Proyecto con Docker Compose**

Para levantar los contenedores del proyecto y asegurarte de que se construyan con la configuración más reciente, ejecuta el siguiente comando:

```bash
docker-compose up --build
```

### Endpoints Disponibles

Una vez que el proyecto esté en funcionamiento, puedes interactuar con los siguientes endpoints:

- **`/cuentas`**: Obtiene información relacionada con las cuentas.
- **`/clientes`**: Obtiene los datos de los clientes.
- **`/movimientos`**: Consulta los movimientos registrados en el sistema.

