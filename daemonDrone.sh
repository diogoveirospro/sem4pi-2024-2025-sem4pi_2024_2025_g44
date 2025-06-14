#!/usr/bin/env bash

# shellcheck disable=SC2125
SHODRONE_CP=shodrone.daemon.drone/target/shodrone.daemon.drone-0.1.0.jar:shodrone.daemon.drone/target/dependency/*

java -cp "$SHODRONE_CP" Server.DaemonDroneRunnerBootstrapper