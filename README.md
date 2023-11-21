# Proyecto_APIRest_JWT

## Descripción del Proyecto

Este proyecto consiste en una API Rest desarrollada con Java y Spring Boot. Proporciona funciones de autenticación de usuarios con roles, permitiendo a los usuarios registrarse, autenticarse, modificar sus datos y acceder a recursos protegidos según su rol. La seguridad se gestiona mediante JSON Web Token (JWT).

## Funcionalidades

- **Autenticación Segura:** Los usuarios pueden registrarse y autenticarse de forma segura mediante JWT.
- **Generación de Token:** JWT se utiliza para generar tokens de acceso después de una exitosa autenticación.
- **Validación de Token:** Se implementa la validación de JWT para garantizar la integridad del token y verificar su autenticidad.
- **Roles en el Token:** Los roles del usuario se incluyen en el token JWT, determinando así sus permisos.
- **Roles de Usuario:** Se implementan roles como "USER" y "ADMIN" para controlar el acceso a recursos específicos.

## Habilidades Desarrolladas

- **Java y Spring Boot:** Desarrollo del backend y gestión de las operaciones de la API..

- **JWT (JSON Web Token):** Manejo de seguridad mediante la generación y validación de tokens.

- **Seguridad con Spring Security:** Implementé Spring Security para gestionar la autenticación y autorización, asegurando las rutas y funciones de la aplicación.

- **Manejo de Sesiones:** Implementación de gestión de sesiones de usuario para mantener la autenticación durante la interacción del usuario con la aplicación.
  
- **Git y GitHub:** Utilicé Git y GitHub para controlar versiones y colaborar eficientemente en el desarrollo del proyecto.

## Cómo Clonar y Ejecutar Localmente

1. Abre git bash y clona este repositorio en tu máquina local:
` git clone https://github.com/Porico94/Proyecto_APIRest_JWT` 

2. Abre el proyecto en tu IDE preferido (Eclipse, IntelliJ, etc.).

## Consideraciones previas

1. Modificar el archivo application.properties según software de base de datos que estes usando, tienes que configurar el puerto, nombre de tu base de datos, username, password y driver. (En este proyecto se está usando MySQL).

## Modo de Uso con Postman

#### Autenticación de Usuario

1. Registrarse:
   
  - Enviar una solicitud POST a http://localhost:8080/api/auth/register con el siguiente cuerpo en formato JSON:

     `{
    "firstName": "Juan",
    "lastName": "Martinez",
    "email": "jmartinez@ejemplo.com",
    "password": "123456"
    }`

  - Recibirás un token JWT en la respuesta, este token inicial generalmente tiene una duración más corta y está destinado a validar que la autenticación durante el registro fue exitosa y puede contener información básica sobre el usuario, pero por lo general no incluye roles o permisos detallados 
    
2. Iniciar sesión:
  - Enviar una solicitud POST a http://localhost:8080/api/auth/authenticate con el siguiente cuerpo en formato JSON:
  
     `{
         "email": "jmartinez@ejemplo.com",
         "password": "123456"
    }`

  - Recibirás un nuevo token JWT en la respuesta, este token tiene una duración más larga, es más robusto y suele incluir roles y permisos detallados del usuario.
  - Este nuevo token de autenticación se utiliza para acceder a recursos protegidos y realizar operaciones en nombre del usuario autenticado.
      
3. Acceso a Recursos Protegidos:
  - Enviar una solicitud GET a un recurso protegido, por ejemplo, http://localhost:8080/api/greeting/sayHelloProtected
  - Agregar el token JWT en la pestaña Authorization y en el type elige Bearer Token.

## Capturas de Pantalla

Registro
![Captura de Pantalla 1](https://raw.githubusercontent.com/Porico94/Proyecto_APIRest_JWT/master/Capturas%20de%20Pantalla/APIRest%20Register.png)

Autenticación
![Captura de Pantalla 2](https://raw.githubusercontent.com/Porico94/Proyecto_APIRest_JWT/master/Capturas%20de%20Pantalla/APIRest%20Authenticate.png)

Acceso a Recursos
![Captura de Pantalla 3](https://github.com/Porico94/Proyecto_APIRest_JWT/blob/master/Capturas%20de%20Pantalla/APIRest%20Access.png)

## Tecnologías Utilizadas

  - Java y Spring Boot: Desarrollo del backend y gestión de las operaciones de la API.

  - Spring Security: Implementación de seguridad para autenticación y autorización.

  - JWT (JSON Web Token): Manejo de seguridad mediante la generación y validación de tokens.

  - Git y GitHub: Control de versiones y colaboración eficiente en el desarrollo del proyecto.

## Autor

Pool Rimari Córdova
