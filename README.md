# API REST - Gestión de Usuarios

Microservicio backend desarrollado en Spring Boot 4 para la gestión de usuarios, implementando buenas prácticas de seguridad, validaciones y documentación.

## Tecnologías Utilizadas
* **Java 21**
* **Spring Boot 3** (Web, Data JPA, Validation)
* **MySQL** (Base de datos relacional)
* **Flyway** (Control de versiones de base de datos)
* **Spring Security (BCrypt)** (Encriptación de contraseñas)
* **Springdoc OpenAPI (Swagger)** (Documentación interactiva)
* **JUnit 5 & Mockito** (Pruebas unitarias y de integración)

## Configuración y Ejecución

1. **Clonar el repositorio.**
2. **Configurar las variables de entorno:**
   * Crea un archivo llamado `.env` en la raiz.
   * Copia el contenido de `.env.example` y pegalo en tu nuevo `.env`.
   * Sustituye los valores con las credenciales de tu base de datos MySQL local.
3. **Crear la base de datos:** 
Abre tu gestor de base de datos y crea la base de datos vacia que definiste en tu `.env`:
`CREATE DATABASE db_usuarios;`
4. **Ejecutar la aplicación:**
Puedes iniciar el proyecto desde tu IDE o usando Maven:
`mvn spring-boot:run`
Nota: Flyway se encargará de crear y actualizar las tablas de la base de datos automáticamente al iniciar.
5. **Acceder a la documentación interactiva (Swagger) en:**
   `http://localhost:8081/swagger-ui/index.html`

## Endpoints Principales
* `POST /api/v1/usuarios` - Registrar usuario (contraseña encriptada automáticamente).
* `GET /api/v1/usuarios/{id}` - Consultar usuario por ID.
* `PUT /api/v1/usuarios/{id}` - Actualización completa.
* `PATCH /api/v1/usuarios/{id}` - Actualización parcial (soporta campos opcionales).
* `DELETE /api/v1/usuarios/{id}` - Eliminar usuario.