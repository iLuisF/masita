# KAAB

Para ejecutar el proyecto, se debe de crear en mysql un usuario con privilegios:

```
create user 'mayra'@'localhost' identified by 'welce';
grant all privileges on masita.* to mayra@localhost;
```

**Nota**

Si quieren cambiar el nombre del usuario, también es necesario hacerlo en el archivo persistence.xml. Esto se hace modificando las siguientes líneas.

```
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/masita"/>
<property name="javax.persistence.jdbc.user" value="mayra"/>
<property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
<property name="javax.persistence.jdbc.password" value="welce"/>
```

En mysql, hay que crear una base de datos con el comando:
```
create database masita;
```

Después de eso, hay que cargar el script proyecto-comida.sql para que se construya el modelo de la base de datos. Una forma de hacerlo puede ser de la siguiente forma:

```
source proyecto-comida.sql
```

Asimismo, insertamos algunos valores:

```
source poblar.sql
```

# Compilar el proyecto

Dentro de Netbeans, cargar el proyecto y seguir los siguientes pasos:

1. Clean and build Project (masita Maven Webapp)
2. Run Project (seleccionar como servidor Apache Tomcat 8.0.27.0

**Nota:** Usar Java 8
