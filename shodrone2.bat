@ECHO OFF
SETLOCAL ENABLEDELAYEDEXPANSION

ECHO Checking if build is necessary...

:: Define paths
SET "PROJECT_DIR=%~dp0"
SET "TARGET_DIR=%PROJECT_DIR%shodrone.app2\target"
SET "JAR_FILE=%TARGET_DIR%\shodrone.app2-0.1.0.jar"
SET "HASH_FILE=%PROJECT_DIR%\hash_builds\.build_hash"

:: Initialize empty hash string
SET "SOURCE_HASH="

:: Loop through all Java files and pom.xml, compute hashes, and concatenate them
FOR /R "%PROJECT_DIR%" %%F IN (*.java pom.xml) DO (
    :: Exclude files in the target directory
    SET "FILE=%%F"

    FOR /F %%H IN ('certutil -hashfile "%%F" SHA256 ^| FINDSTR /V /C:"hash" /C:"CertUtil"') DO (
       SET "SOURCE_HASH=!SOURCE_HASH!%%H"
    )
)

:: Create a new hash of the concatenated hashes
:: Compute final hash of all source files (concatenated hash string)
SET "SOURCE_HASH_FINAL="
FOR /F %%H IN ("!SOURCE_HASH!") DO (
    SET "SOURCE_HASH_FINAL=!SOURCE_HASH_FINAL!%%H"
)

:: Remove all spaces from SOURCE_HASH_FINAL
CALL :RemoveSpaces "!SOURCE_HASH_FINAL!" SOURCE_HASH_FINAL

ECHO Computed Source Hash: !SOURCE_HASH_FINAL!

:: Load previous hash if it exists
IF EXIST "%HASH_FILE%" (
    SET /P PREVIOUS_HASH=<"%HASH_FILE%"
    ECHO Previous Source Hash: !PREVIOUS_HASH!

    :: Remove all spaces from PREVIOUS_HASH
    CALL :RemoveSpaces "!PREVIOUS_HASH!" PREVIOUS_HASH
) ELSE (
    SET "PREVIOUS_HASH="
    ECHO No previous hash found. This may be the first run.
)

:: Check if JAR exists and compare hashes
IF EXIST "%JAR_FILE%" (
    IF "!SOURCE_HASH_FINAL!"=="!PREVIOUS_HASH!" (
        ECHO No changes detected in source files. Skipping build.
        GOTO :RUN
    )
)

:: Changes detected or first-time build
ECHO Changes detected or first-time build required. Running build-all.bat...
CALL "%PROJECT_DIR%build-all.bat" %1

IF %ERRORLEVEL% NEQ 0 (
    ECHO Build failed. Not updating hash.
    EXIT /B 1
)

:: Save the new hash if build was successful
ECHO !SOURCE_HASH_FINAL! > "%HASH_FILE%"
ECHO Build completed successfully. Updated hash saved.

:: Run the application
:RUN
ECHO Running the application...
CD /D "%TARGET_DIR%"
java -jar shodrone.app2-0.1.0.jar

:: RemoveSpaces function to remove all spaces
:RemoveSpaces
SET "INPUT=%~1"
SET "OUTPUT="
:: Loop through each character and remove spaces
FOR /L %%A IN (0,1,255) DO (
    SET "CHAR=!INPUT:~%%A,1!"
    IF NOT "!CHAR!"=="" (
        IF NOT "!CHAR!"==" " SET "OUTPUT=!OUTPUT!!CHAR!"
    )
)
SET "%~2=!OUTPUT!"
EXIT /B
