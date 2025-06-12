#!/usr/bin/env bash

# shellcheck disable=SC2125
SHODRONE_CP=shodrone.daemon.simulator/target/shodrone.daemon.simulator-0.1.0.jar:shodrone.daemon.simulator/target/dependency/*

java -cp "$SHODRONE_CP" Server.DaemonSimulatorBootstrapper