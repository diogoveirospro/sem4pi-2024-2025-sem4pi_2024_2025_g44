@ECHO OFF
ECHO Running build-all.bat...
CALL "%~dp0build-all.bat" %1

:: Change directory to the target folder
CD /D "%~dp0shodrone.app1\target"

:: Run the application
ECHO Running the application...
java -jar shodrone.app1-0.1.0.jar
