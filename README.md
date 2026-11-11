#  Primera entrega

##  Estructura csv
id,destino,origen,nombre

##  Explicacion lenguaje

He usado Java, en la primera entrega realicé el proyecto utilizando un proyecto Java IntellIJ, version 22 amazon coretto. Lo he usado por que se me hace más fácil y cómodo trabajar mediante un POO y como java es el que más estoy familiarizado pues también se ha usado por eso.

Diagrama: 
<img width="1835" height="1625" alt="image" src="https://github.com/user-attachments/assets/5956904c-89f8-41ef-bc10-53b18a8a45fb" />

#  Segunda entrega
##  Stack tecnológico

Java gradle (version 22 de amazon coretto) + Springboot (usando hibernate y su conector con SQL) y SQL en un contenedor docker.

Para hacer la segunda entrega me he topado con un principal problema: la incompatibilidad del proyecto nativo de IntellIJ para añadir librerias. Para añadir librerias sin importar en intellij es demasiado complicado, así que migré todo mi proyecto a gradle, pudiendo así añadir con éxito Springboot.

En SpringBoot he usado Hibernate, como ORM para poder pasar las clases a tablas de forma más sencilla y escalable, sin insertar strings como consultas de sql.
Tambíen usando su conector con SQL claro.

Por ello he tenido que aprender las bases de como funcionan los modelos ORM y Hibernate, empezando así:

Las clases, que serán las próximas talas son "modelos", así que por ende las he guardado en la carpeta modelos, donde en estos definimos a cada atributo el tipo de columna que sería en la tabla, poniendo a la clase total el nombre de su tabla, poniendo restricciones a cada atributo de la clase (para que las restricciones se apliquen al crear las tablas a sus respectivas columnas)

Lo he pensado como: 
Clase/Entidad -> Tabla
Atributo(s) -> columna(s) de la tabla.

Y luego a cada clase/modelo se le ha de aplicar su versión repositorio, guardados en la carpeta repositorios.

Por lo que he aprendido la carpeta repositorios es elmque hace la conexión y se contacta con sql y sus datos. Es omo que cada modelo tiene su caja de herramientas (repositorio guarda las funciones que se contactan con sql, y tamboén te permite hacer funciones personalizadas con consultas sql personalizadas)) y el técnico (ya que también se contacta y hace las modificaciones pertinentes)

Una vez hecho eso SpringBoot también me ha obligado a tener toda la lógica del main en cargarDatos y el verdadero solo llama a cargarDatos.

#  Guía de Uso

**Importante, tener la version de Java 22 (JDK 22 de Amazon Coretto)**, si no la tienes instalala y aplicala al proyecto de intellij

Primero extraes el zip del proyecto instalado desde github, en la raiz del directorio (dentro de la carpeta, donde tiene todos los archivos y carpetas de java...) accede ahí con la consola de comandos y haz este comando para levantar la imagen de sql pertinente:
(asegurate de tener el docker desktop iniciado y corriendo)

docker compose up -d

Una vez se te levante la imagen pon el proyecto en intellij y ya puedes iniciarlo correctamente.

Si quieres revisar la BDD de SQL, tienes que revisar el ID del contenedor que hemos creado y poner este comando en el mismo lugar:

docker exec -it <id del contenedor> mysql -u root -p

Una vez eso te pedirá una contraseña, la contraseña es "root"

Una vez dentro de mySQL pon "USE airTortilla" y ya estarás en el schema de AirTortilla, podrás consultar todas las tablas, si los registros están realmente guardados (mediante consultas SQL)

Por ejemplo poniendo "SHOW TABLES" te mostrará todas las tablas creadas

O poniendo "SELECT * FROM usuarios" te mostrará los usuarios.

Puedes ir revisando mediante este sistema si la BDD es crrecta, si el guardado se realiza correctamente, si los usuarios se crean...





