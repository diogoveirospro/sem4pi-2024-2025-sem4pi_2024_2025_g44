@echo off

set SHODRONE_CP=shodrone.daemon.user\target\shodrone.daemon.user-0.1.0.jar;shodrone.daemon.user\target\dependency\*

java --add-opens java.base/java.time=ALL-UNNAMED -cp "%SHODRONE_CP%" Server.DaemonUserBootstrapper