Apache Tomcat 8.5.49 build with Apache Maven

### import to JetBrains IDEA

* open tomcat-src
* Run -> Edit Configurations
    * Main Class : org.apache.catalina.startup.Bootstrap
    * VM options : -Dcatalina.home=catalina-home
                   -Dcatalina.base=catalina-home
                   -Djava.endorsed.dirs=catalina-home/endorsed
                   -Djava.io.tmpdir=catalina-home/temp
                   -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager
                   -Djava.util.logging.config.file=catalina-home/conf/logging.properties
                   -Duser.language=en
                   -Duser.region=CA
                   -Dfile.encoding=UTF-8
                   -Dsun.jnu.encoding=UTF-8
* Run/Debug Bootstrap
* visit http://localhost:8080/ 

