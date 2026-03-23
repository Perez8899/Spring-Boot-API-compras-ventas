## 🛒 Sistema de Compras y Ventas - API REST

📋 Descripción
```bash
API RESTful para la gestión de compras y ventas. 
El sistema permite administrar productos, clientes, proveedores, y procesar transacciones de 
compra y venta con autenticación JWT y roles de usuario.
```

## 🚀 Tecnologías Utilizadas
```bash
Categoría	Tecnologías
Lenguaje	Java 21
Framework	Spring Boot 3.4.5, Spring Security
Base de Datos	PostgreSQL
Persistencia	Spring Data JPA, Hibernate
Autenticación	JWT (JSON Web Token)
Documentación	SpringDoc OpenAPI (Swagger UI)
Validaciones	Spring Validation, JavaFaker (datos de prueba)
Herramientas	Lombok, Maven, DevTools
```

## ⚙️ Requisitos Previos
```bash
Java 21 o superior

Maven 3.8+

PostgreSQL 14+ o Docker

Git
```

## 🔧 Instalación y Ejecución
```bash
1. Clonar el repositorio
  
   git clone https://github.com/Perez8899/Spring-Boot-API-purchases-and-sales.git
   cd Spring-Boot-API-purchases-and-sales

2. Configurar base de datos
   Crear una base de datos en PostgreSQL:


CREATE DATABASE compras_ventas_db;
Configurar las credenciales en src/main/resources/application.properties:


spring.datasource.url=jdbc:postgresql://localhost:5432/compras_ventas_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3. Ejecutar la aplicación
  
   mvn spring-boot:run
   La aplicación estará disponible en: http://localhost:8080
   ```
## Levantar los servicios con Docker Compose

```bash
docker-compose up -d
Este comando levantará:

PostgreSQL en el puerto 5432

Aplicación Spring Boot en el puerto 8080
```
## 🏗️ Arquitectura del Proyecto

```bash
src/main/java/com/perez/comprasventas/
├── controller/      # Controladores REST
├── service/         # Lógica de negocio
├── repository/      # Interfaces JPA
├── model/           # Entidades JPA
├── dto/             # Objetos de transferencia de datos
├── auth/        # Configuración de Spring Security y JWT
├── config/          # Configuraciones adicionales
└── utils/           # Utilidades y helpers
```


## 👨‍💻 Autor
```bash
Héctor José Pérez
GitHub: @Perez8899
Email: hectorjp43@gmail.com
```
