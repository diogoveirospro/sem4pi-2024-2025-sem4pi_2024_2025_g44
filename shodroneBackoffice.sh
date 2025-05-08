#!/usr/bin/env bash
echo OFF
echo make sure JAVA_HOME is set to JDK folder
echo make sure maven is on the system PATH
mvn $1 dependency:copy-dependencies package

# shellcheck disable=SC2125
SHODRONE_CP=shodrone.app.backoffice/target/shodrone.app.backoffice-0.1.0.jar:shodrone.app.backoffice/target/dependency/*

java -cp "$SHODRONE_CP" Shodrone.ShodroneBackoffice