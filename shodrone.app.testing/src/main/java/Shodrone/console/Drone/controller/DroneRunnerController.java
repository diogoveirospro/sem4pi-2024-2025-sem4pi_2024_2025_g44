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

    public void sendFileToServer(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                System.out.println("File does not exist: " + filePath);
                return;
            }

            StringBuilder formattedContent = new StringBuilder();
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            for (int i = 0; i < lines.size(); i++) {
                String[] coordinates = lines.get(i).split(",");
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

            server.sendFileToServer(filePath ,formattedContent.toString());

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}