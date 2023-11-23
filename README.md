# Proyecto Spring WebFlux

Este es un proyecto base de Spring WebFlux que utiliza Java 21 e incluye los siguientes componentes:

1. **zipkin-server**: Un servidor Zipkin para la trazabilidad de solicitudes.

2. **config-server**: Un servidor de configuración que se conecta a un repositorio de configuración remoto (en este caso se configuro para que utilize un git local) y proporciona configuración a otros servicios.

3. **eureka-server**: Un servidor de registro Eureka para la administración de servicios y la resolución de nombres.

4. **gateway-server**: Un gateway que actúa como punto de entrada para las solicitudes a otros servicios. 

5. **microservicio**: Un microservicio de ejemplo que puede implementarse según las necesidades específicas.

6. **auth-service**: Un servidor de autorización que proporciona tokens de acceso JWT para los servicios que lo soliciten.

7. **user-service**: Un servicio de usuarios que proporciona información de usuarios.

## Requisitos

- Java 21 o superior.
- Docker.
- postman.

## Configuración

Cada uno de los componentes mencionados anteriormente se encuentra en su propio directorio con su respectiva configuración. Asegúrate de configurar cada componente según tus necesidades antes de iniciar el proyecto.

## Ejecución
### Ejecución docker
1. ejecuta el script `./build_image_docker.bat` para construir las imágenes de docker.
2. ejecuta el comando `docker-compose up` para iniciar los contenedores.

### Ejecución local
Puedes ejecutar cada uno de los proyectos de manera local con el comando `./gradlew bootRun` o `gradlew.bat bootRun` según sea el caso.
El orden de ejecución es el siguiente:
1. zipkin-server (opcional)
2. config-server
3. eureka-server
4. gateway-server
5. los demás servicios.
