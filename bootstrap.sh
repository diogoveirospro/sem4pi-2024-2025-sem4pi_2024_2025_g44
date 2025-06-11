#!/usr/bin/env bash

# Define o classpath (JAR + dependências)
# shellcheck disable=SC2125
SHODRONE_CP=shodrone.backoffice.bootstrap/target/bootstrap-0.1.0.jar:shodrone.backoffice.bootstrap/target/dependency/*

# Executa a aplicação Java
java --add-opens java.base/java.time=ALL-UNNAMED -cp "$SHODRONE_CP" shodrone.ShodroneBootstrap