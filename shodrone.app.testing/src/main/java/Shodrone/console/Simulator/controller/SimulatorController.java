package Shodrone.console.Simulator.controller;

import Shodrone.Server.SimulatorAppProtocolProxy;
import Shodrone.exceptions.FailedRequestException;
import eapli.framework.application.UseCaseController;
import shodrone.presentation.UtilsUI;

import java.io.IOException;

@UseCaseController
public class SimulatorController {
    private final SimulatorAppProtocolProxy server = new SimulatorAppProtocolProxy();

    public void editConfigFile(String configFileName, String inputDirectory, int maxCollisions, int numDrones, int droneRadius, int xMax, int yMax, int zMax, int timeStep) throws Exception {
        server.sendEditConfigRequest(configFileName, inputDirectory, maxCollisions, numDrones, droneRadius, xMax, yMax, zMax, timeStep);
    }


    public void simulateAndGenerateReport(String path) throws FailedRequestException, IOException {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        server.generateSimulationReport(path);
    }
}
