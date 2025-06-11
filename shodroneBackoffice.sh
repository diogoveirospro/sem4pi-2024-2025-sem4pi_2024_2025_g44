#!/usr/bin/env bash

# shellcheck disable=SC2125
SHODRONE_CP=shodrone.app.backoffice/target/shodrone.app.backoffice-0.1.0.jar:shodrone.app.backoffice/target/dependency/*

java -cp "$SHODRONE_CP" Shodrone.ShodroneBackoffice