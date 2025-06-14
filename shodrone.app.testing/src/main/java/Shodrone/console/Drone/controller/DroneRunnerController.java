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
            Path absolutePath = Paths.get(filePath).toAbsolutePath().normalize();

            if (!Files.exists(absolutePath)) {
                System.out.println("File does not exist: " + absolutePath);
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

            String relativePath = filePath.replace("\\", "/");
            int index = relativePath.indexOf("DroneFiles");
            if (index != -1) {
                relativePath = "/sem4pi-2024-2025-sem4pi_2025_g44/SCOMP/srcs/" + relativePath.substring(index);
            } else {
                System.out.println("ERROR: File is not in DroneFiles directory.");
                return;
            }

            server.sendFileToServer(relativePath, formattedContent.toString());

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


}