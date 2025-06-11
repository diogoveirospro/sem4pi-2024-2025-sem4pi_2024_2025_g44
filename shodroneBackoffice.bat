REM Define the classpath (JAR + dependencies)
set "SHODRONE_CP=shodrone.app.backoffice\target\shodrone.app.backoffice-0.1.0.jar;shodrone.app.backoffice\target\dependency\*"

REM Run the Java application
java --add-opens java.base/java.time=ALL-UNNAMED -cp %SHODRONE_CP% shodrone.ShodroneBootstrap
