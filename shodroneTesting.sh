#!/usr/bin/env bash

# shellcheck disable=SC2125
SHODRONE_CP=shodrone.app.testing/target/shodrone.app.testing-0.1.0.jar:shodrone.app.testing/target/dependency/*

java -cp "$SHODRONE_CP" Shodrone.ShodroneTesting