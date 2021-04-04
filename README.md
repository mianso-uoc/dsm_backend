# REST API con Spring Boot, PostgreSQL, JPA

## Puesta en marcha

**1. Clonar aplicación**

```bash
https://github.com/mianso-uoc/dsm_backend.git
```

**2. Crear base de datos postgres**

```bash
create database postgres
```

**3. Actualizar configuración de usuario y password de la base de datos**

+ abrir `src/main/resources/application.properties`

+ cambiar datos `spring.datasource.username` y `spring.datasource.password`

**4. Poner en marcha la aplicación con maven**

```bash
mvn spring-boot:run
```

La aplicación se pondrá en marcha en <http://localhost:8080>.

## APIs REST

Se definen los siguientes métodos:

    GET /api/manufacturers
    
    POST /api/manufacturers
    
    GET /api/manufacturers/{manufacturerId}
    
    PUT /api/manufacturers/{manufacturerId}
    
    DELETE /api/manufacturers/{manufacturerId}
