package Shodrone.requests;

public class EditConfigRequest {
    private final String configFileName;
    private final String inputDirectory;
    private final int maxCollisions;
    private final int numDrones;
    private final int droneRadius;
    private final int xMax;
    private final int yMax;
    private final int zMax;
    private final int timeStep;

    public EditConfigRequest(String configFileName, String inputDirectory, int maxCollisions, int numDrones,
                             int droneRadius, int xMax, int yMax, int zMax, int timeStep) {
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

    public String toRequest() {
        return String.format("EDIT_CONFIG %s %s %d %d %d %d %d %d %d",
                configFileName, inputDirectory, maxCollisions, numDrones, droneRadius, xMax, yMax, zMax, timeStep);
    }

    public String configFileName() {
        return configFileName;
    }

    public String inputDirectory() {
        return inputDirectory;
    }

    public int maxCollisions() {
        return maxCollisions;
    }

    public int numDrones() {
        return numDrones;
    }

    public int droneRadius() {
        return droneRadius;
    }

    public int xMax() {
        return xMax;
    }

    public int yMax() {
        return yMax;
    }

    public int zMax() {
        return zMax;
    }

    public int timeStep() {
        return timeStep;
    }
}