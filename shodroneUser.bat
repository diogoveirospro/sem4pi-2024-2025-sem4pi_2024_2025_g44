REM Define the classpath (JAR + dependencies)
set "SHODRONE_CP=shodrone.app.user\target\shodrone.app.user-0.1.0.jar;shodrone.app.user\target\dependency\*"

REM Run the Java application
java -cp %SHODRONE_CP% Shodrone.ShodroneUserApp