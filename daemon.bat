@echo off

set SHODRONE_CP=shodrone.daemon.user\target\shodrone.daemon.user-0.1.0.jar;shodrone.daemon.user\target\dependency\*

java -cp "%SHODRONE_CP%" Server.DaemonUserBootstrapper