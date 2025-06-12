package Server.protocol;

import core.Daemon.customerApp.Controller.UserAppServerController;
import core.Daemon.simulation.Controller.SimulatorServerController;

public class EditConfigRequest extends UserAppRequest {
    private final String configFileName;
    private final String inputDirectory;
    private final int maxCollisions;
    private final int numDrones;
    private final int droneRadius;
    private final int xMax;
    private final int yMax;
    private final int zMax;
    private final int timeStep;

    public EditConfigRequest(final SimulatorServerController controller, final String inputRequest,
                             final String configFileName, final String inputDirectory, final int maxCollisions,
                             final int numDrones, final int droneRadius, final int xMax, final int yMax,
                             final int zMax, final int timeStep) {
        super(controller, inputRequest);
        this.configFileName = configFileName;
        this.inputDirectory = inputDirectory;
        this.maxCollisions = maxCollisions;
        this.numDrones = numDrones;
        this.droneRadius = droneRadius;
        this.xMax = xMax;
        this.yMax = yMax;
        this.zMax = zMax;
        this.timeStep = timeStep;
    }

    @Override
    public String execute() {
        try {
            boolean success = controller.editConfig(configFileName, inputDirectory, maxCollisions, numDrones,
                    droneRadius, xMax, yMax, zMax, timeStep);
            return buildResponse(success);
        } catch (Exception e) {
            return buildServerError("An error occurred while processing the request");
        }
    }

    private String buildResponse(final boolean success) {
        StringBuilder response = new StringBuilder();
        if (success) {
            response.append("CONFIG_EDITED, \"Configuration file edited successfully\"\n");
        } else {
            response.append("CONFIG_EDIT_FAILED, \"Failed to edit configuration file\"\n");
        }
        return response.toString();
    }
}