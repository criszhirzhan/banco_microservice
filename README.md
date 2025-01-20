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

## Endpoints Disponibles

Una vez que el proyecto esté en funcionamiento, puedes interactuar con los siguientes endpoints:

- **`/cuentas`**: Obtiene información relacionada con las cuentas.
- **`/clientes`**: Obtiene los datos de los clientes.
- **`/movimientos`**: Consulta los movimientos registrados en el sistema.

## ClienteController API Documentation

## Endpoints

### 1. **Crear un Cliente**
#### `POST /clientes`
Permite agregar un nuevo cliente al sistema.

- **Request Body**:
  - `nombre` (String): Nombre del cliente.
  - `genero` (String): Género del cliente.
  - `estado` (String): Estado del cliente.
  - `edad` (int): Edad del cliente.
  - `direccion` (String): Direccion del cliente.
  - `telefono` (String): Telefono del cliente.
  - `password` (String): Password del cliente.

- **Response**: 
  - `message` (String): Mensaje de éxito o error de la operación.

---

### 2. **Obtener Clientes con Filtro**
#### `GET /clientes`
Permite obtener una lista de clientes con filtros opcionales.

- **Query Parameters**:
  - `nombre` (String, opcional): Filtra los clientes por nombre.
  - `genero` (String, opcional): Filtra los clientes por género.
  - `estado` (String, opcional): Filtra los clientes por estado.
  - `identificacion` (String, opcional): Filtra los clientes por identificación.

- **Response**: 
  - Devuelve una lista paginada de clientes que coinciden con los filtros proporcionados.

---

### 3. **Actualizar Cliente**
#### `PUT /clientes/{id}`
Permite actualizar la información de un cliente existente.

- **Path Parameter**:
  - `id` (Long, requerido): ID del cliente a actualizar.

- **Request Body**:
  - `nombre` (String): Nombre del cliente.
  - `genero` (String): Género del cliente.
  - `edad` (int): Edad del cliente.
  - `direccion` (String): Direccion del cliente.
  - `telefono` (String): Telefono del cliente.
  - `password` (String): Password del cliente.

- **Response**:
  - `message` (String): Mensaje de éxito o error de la operación.

---

### 4. **Eliminar Cliente**
#### `DELETE /clientes/{id}`
Permite eliminar un cliente del sistema.

- **Path Parameter**:
  - `id` (Long, requerido): ID del cliente a eliminar.

- **Response**:
  - `message` (String): Mensaje de éxito o error de la operación.

---

## Descripción de Parámetros

- **nombre**: Nombre del cliente.
- **genero**: Género del cliente (por ejemplo, "M", "F").
- **estado**: Estado del cliente (por ejemplo, "ACTIVO", "INACTIVO").
- **identificacion**: Identificación única del cliente.
- **id**: ID del cliente en el sistema para realizar operaciones de actualización o eliminación.


## CuentaController API Documentation

## Endpoints

### 1. **Crear una Cuenta**
#### `POST /cuentas`
Permite agregar una nueva cuenta al sistema.

- **Request Body**:
  - `numeroCuenta` (String): Número de la cuenta.
  - `idCliente` (in): ID del cliente titular de la cuenta.
  - `tipoCuenta` (String): Tipo de cuenta (por ejemplo, "Ahorros", "Corriente").
  - `estado` (String): Estado de la cuenta (por ejemplo, "ACTIVO", "INACTIVO").
  - `saldoInicial` (Double): Saldo inicial de la cuenta (por ejemplo, 300, 12.45).

- **Response**: 
  - `message` (String): Mensaje de éxito o error de la operación.

---

### 2. **Obtener Cuentas con Filtro**
#### `GET /cuentas`
Permite obtener una lista de cuentas con filtros opcionales.

- **Query Parameters**:
  - `cedula` (String, opcional): Filtra las cuentas por cédula del titular.
  - `numeroCuenta` (String, opcional): Filtra las cuentas por número de cuenta.
  - `tipoCuenta` (String, opcional): Filtra las cuentas por tipo de cuenta.
  - `estado` (String, opcional): Filtra las cuentas por estado.

- **Response**: 
  - Devuelve una lista paginada de cuentas que coinciden con los filtros proporcionados.

---

### 3. **Eliminar Cuenta**
#### `DELETE /cuentas/{id}`
Permite eliminar una cuenta del sistema.

- **Path Parameter**:
  - `id` (Long, requerido): ID de la cuenta a eliminar.

- **Response**:
  - `message` (String): Mensaje de éxito o error de la operación.

---

## Descripción de Parámetros

- **numeroCuenta**: Número único de la cuenta.
- **cedula**: Cédula del titular de la cuenta.
- **tipoCuenta**: Tipo de cuenta (por ejemplo, "Ahorros", "Corriente").
- **estado**: Estado de la cuenta (por ejemplo, "ACTIVO", "INACTIVO").
- **id**: ID de la cuenta en el sistema para realizar operaciones de eliminación.


## MovimientoController API Documentation

## Endpoints

### 1. **Crear un Movimiento**
#### `POST /movimientos`
Permite agregar un nuevo movimiento a una cuenta.

- **Request Body**:
  - `idCuenta` (String): Número de la cuenta a la que se aplica el movimiento.
  - `valor` (Double): Monto del movimiento.

- **Response**:
  - `message` (String): Mensaje de éxito o error de la operación.

---

## Descripción de Parámetros

- **idCuenta**: ID de la cuenta a la que se aplicará el movimiento.
- **valor**: El monto de dinero que se va a depositar o retirar.

## ReportesController API Documentation

## Endpoints

### 1. **Obtener Reporte con Filtros**
#### `GET /reportes`
Permite obtener un reporte filtrado por cédula y fechas de inicio y fin.

- **Request Parameters**:
  - `cedula` (String, opcional): Cédula del cliente para filtrar el reporte.
  - `fechaInicio` (String, opcional, formato ISO 8601): Fecha de inicio para filtrar el reporte.
  - `fechaFin` (String, opcional, formato ISO 8601): Fecha de fin para filtrar el reporte.

- **Response**:
  - `reporte` (ReporteDTO): Información del reporte generado, que contiene los detalles del reporte solicitado.

---

## Descripción de Parámetros

- **cedula**: Cédula del cliente, usada para filtrar los reportes de ese cliente específico.
- **fechaInicio**: Fecha de inicio para el rango de reporte (en formato ISO 8601) (por ejemplo 01/01/2025).
- **fechaFin**: Fecha de fin para el rango de reporte (en formato ISO 8601) (por ejemplo 20/01/2025).







