package Shodrone.console.Simulator.controller;

import Shodrone.Server.SimulatorAppProtocolProxy;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class SimulatorController {
    private final SimulatorAppProtocolProxy server = new SimulatorAppProtocolProxy();

    public void editConfigFile(String configFileName, String inputDirectory, int maxCollisions, int numDrones, int droneRadius, int xMax, int yMax, int zMax, int timeStep) throws Exception {
        server.sendEditConfigRequest(configFileName, inputDirectory, maxCollisions, numDrones, droneRadius, xMax, yMax, zMax, timeStep);
    }


}
