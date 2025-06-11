REM Define o classpath (JAR + dependências)
set "SHODRONE_CP=shodrone.backoffice.bootstrap\target\bootstrap-0.1.0.jar;shodrone.backoffice.bootstrap\target\dependency\*"

REM Executa a aplicação Java
java --add-opens java.base/java.time=ALL-UNNAMED -cp %SHODRONE_CP% shodrone.ShodroneBootstrap