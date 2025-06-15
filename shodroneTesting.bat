REM Define the classpath (JAR + dependencies)
set "SHODRONE_CP=shodrone.app.testing\target\shodrone.app.testing-0.1.0.jar;shodrone.app.testing\target\dependency\*"

REM Run the Java application
java -cp %SHODRONE_CP% Shodrone.ShodroneTesting
