package Shodrone.console.Drone.controller;

import Shodrone.Server.DroneRunnerAppProtocolProxy;
import eapli.framework.application.UseCaseController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@UseCaseController
public class DroneRunnerController {
    private final DroneRunnerAppProtocolProxy server = new DroneRunnerAppProtocolProxy();
    private static final String PATH_PREFIX = "/root/sem4pi-2024-2025-sem4pi_2024_2025_g44/SCOMP/srcs/DroneTests/";

    public void sendFileToServer(String filePath) {
        try {
            Path absolutePath = Paths.get(filePath).toAbsolutePath().normalize();

            if (!Files.exists(absolutePath)) {
                System.out.println("File does not exist: " + absolutePath);

                String cleanedPath = filePath.replace("\\", "/");
                int index = cleanedPath.indexOf("DroneTests/");
                if (index != -1) {
                    String relativeToDroneTests = cleanedPath.substring(index + "DroneTests/".length());
                    String fullServerPath = PATH_PREFIX + relativeToDroneTests;

                    System.out.println("Sending elimination message to server: " + fullServerPath);
                    server.sendFileToServer(fullServerPath, "ELIMINATE");
                } else {
                    System.out.println("ERROR: 'DroneTests' folder not found in path: " + cleanedPath);
                }
                return;
            }

            StringBuilder formattedContent = new StringBuilder();
            List<String> lines = Files.readAllLines(absolutePath, StandardCharsets.UTF_8);

            for (String line : lines) {
                String[] coordinates = line.split(",");
                if (coordinates.length == 3) {
                    formattedContent.append("(")
                            .append(coordinates[0].trim()).append(",")
                            .append(coordinates[1].trim()).append(",")
                            .append(coordinates[2].trim()).append(") ");
                }
            }

            if (formattedContent.length() > 0) {
                formattedContent.setLength(formattedContent.length() - 1);
            }

            String cleanedPath = filePath.replace("\\", "/");
            int index = cleanedPath.indexOf("DroneTests/");
            if (index != -1) {
                String relativeToDroneTests = cleanedPath.substring(index + "DroneTests/".length());
                String fullServerPath = PATH_PREFIX + relativeToDroneTests;

                System.out.println("Sending file to server: " + fullServerPath);
                server.sendFileToServer(fullServerPath, formattedContent.toString());
            } else {
                System.out.println("ERROR: 'DroneTests' folder not found in path: " + cleanedPath);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
