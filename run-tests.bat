@echo off
ECHO Running Maven tests...
mvn test -DfailIfNoTests=false -X

if %ERRORLEVEL% NEQ 0 (
    ECHO Tests failed. Check the output above.
    exit /b 1
)

ECHO Tests completed successfully!

