# Documentacion #
- Maven : https://maven.apache.org/download.cgi
- JDK : https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html 
- Extensiones VS:
  - vscjava.vscode-java-pack
  - redhat.vscode-community-server-connector

## Seteo del pomm ##

**(librerias)**

- MySQL Connector Java : https://mvnrepository.com/artifact/mysql/mysql-connector-java , 8.0.31 (MAVEN)
- Java Servlet API : https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api , 4.0.1 (MAVEN) 
- Java Servlet JSP: https://mvnrepository.com/artifact/javax.servlet.jsp/javax.servlet.jsp-api 2.3.3

**(plugin)**

- para compilar el codigo fuente: https://maven.apache.org/plugins/maven-compiler-plugin/plugin-info.html
- ERROR para agregar pulgin para empaquetar la aplicacion como WARE: no me figura la version 3.3.1 

**Server**

Apache Tomcat Apache Tomcat/9.0.41 (Funciona)
version License URL: https://www.apache.org/licenses/LICENSE-2.0.txt


levantar servidor
maven-> apimovies -> lifecycle ->install ->run
(AVISEN SI NO ANDA Y cambian el puerto) 

chequear http://localhost:8080/

Base de datos
- XAMPP

- Cuidado que nombre le ponen a la base de datos local y el URL que se tiene en DatabaseConnection , por ahora se llama movies pero cambiar el url a como lo tengan localmente
- mysql jbc: https://dev.mysql.com/downloads/connector/j/

