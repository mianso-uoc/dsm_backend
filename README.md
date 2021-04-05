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

	GET ​/api​/users​/{id}
	PUT ​/api​/users​/{id}
	DELETE ​/api​/users​/{id}
	GET ​/api​/users
	POST ​/api​/users
	

	GET ​/api​/manufacturers
	GET ​/api​/manufacturers​/{id}
	POST ​/api​/manufacturers
	PUT ​/api​/manufacturers​/{id}
	DELETE ​/api​/manufacturers​/{id}
	

	GET ​/api​/products​/manufacturer​/{manufacturerId}
	GET ​/api​/products​/{id}
	POST ​/api​/products
	PUT ​/api​/products​/{id}
	DELETE ​/api​/products​/{id}
	

	GET ​/api​/provinces​/{id}
	GET ​/api​/provinces​/{countryId}
	GET ​/api​/countries
	GET ​/api​/countries​/{id}
	GET ​/api​/cities​/{provinceId}
	GET ​/api​/cities​/{id}
