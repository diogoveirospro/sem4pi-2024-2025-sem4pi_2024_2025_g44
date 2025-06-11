#!/usr/bin/env bash

# shellcheck disable=SC2125
SHODRONE_CP=shodrone.app.user/target/shodrone.app.user-0.1.0.jar:shodrone.app.user/target/dependency/*

java --add-opens java.base/java.time=ALL-UNNAMED -cp "$SHODRONE_CP" Shodrone.ShodroneUserApp