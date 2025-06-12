package core.Daemon.simulation.Controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SimulatorServerController {

    private static final String OUTPUT_DIRECTORY = "./ReportFolder";

    public boolean editConfig(String configFileName, String inputDirectory, int maxCollisions, int numDrones, int droneRadius, int xMax, int yMax, int zMax, int timeStep) throws IOException {
        // This will simply edit the configuration file with the provided parameters.

        try {
            if (configFileName == null || configFileName.isEmpty()) {
                throw new IllegalArgumentException("Config file name cannot be null or empty");
            }
            if (inputDirectory == null || inputDirectory.isEmpty()) {
                throw new IllegalArgumentException("Input directory cannot be null or empty");
            }
            if (maxCollisions < 0) {
                throw new IllegalArgumentException("Max collisions must be a non-negative integer");
            }
            if (numDrones <= 0) {
                throw new IllegalArgumentException("Number of drones must be a positive integer");
            }
            if (droneRadius <= 0) {
                throw new IllegalArgumentException("Drone radius must be a positive integer");
            }
            if (xMax <= 0 || yMax <= 0 || zMax <= 0) {
                throw new IllegalArgumentException("Max dimensions must be positive integers");
            }
            if (timeStep <= 0) {
                throw new IllegalArgumentException("Time step must be a positive integer");
            }

            StringBuilder sb = new StringBuilder();
            sb.append("INPUT_DIR=").append(inputDirectory).append("\n");
            sb.append("OUTPUT_DIR=").append(OUTPUT_DIRECTORY).append("\n");
            sb.append("MAX_COLLISIONS=").append(maxCollisions).append("\n");
            sb.append("NUM_DRONES=").append(numDrones).append("\n");
            sb.append("DRONE_RADIUS=").append(droneRadius).append("\n");
            sb.append("X_MAX=").append(xMax).append("\n");
            sb.append("Y_MAX=").append(yMax).append("\n");
            sb.append("Z_MAX=").append(zMax).append("\n");
            sb.append("TIME_STEP=").append(timeStep).append("\n");

            Files.write(Paths.get(configFileName), sb.toString().getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
            return  true;
        } catch (Exception e) {
            // Handle any exceptions that may occur during file operations
            System.err.println("Error editing config file: " + e.getMessage());
            return false;
        }


    }
}
