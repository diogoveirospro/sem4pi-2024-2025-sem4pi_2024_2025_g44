REM Define the classpath (JAR + dependencies)
set "SHODRONE_CP=shodrone.app.user\target\shodrone.app.user-0.1.0.jar;shodrone.app.user\target\dependency\*"

REM Run the Java application
java --add-opens java.base/java.time=ALL-UNNAMED -cp %SHODRONE_CP% Shodrone.ShodroneUserApp