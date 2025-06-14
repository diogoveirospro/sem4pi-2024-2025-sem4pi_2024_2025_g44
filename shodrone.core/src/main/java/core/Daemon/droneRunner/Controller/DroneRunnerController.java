package core.Daemon.droneRunner.Controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DroneRunnerController {
    public boolean sendDroneRunnerFile(String filePath, String fileContent) {
        try {
            filePath = filePath.replace("\\", "/");

            Path targetPath = Paths.get(filePath);

            if ("ELIMINATE".equals(fileContent.trim())) {
                if (Files.exists(targetPath)) {
                    Files.delete(targetPath);
                    System.out.println("File successfully deleted: " + targetPath.toAbsolutePath());
                } else {
                    System.out.println("File does not exist: " + targetPath.toAbsolutePath());
                }
                return true;
            }

            Files.createDirectories(targetPath.getParent());

            String formattedContent = formatFileContent(fileContent);

            Files.write(targetPath, formattedContent.getBytes(StandardCharsets.UTF_8));

            System.out.println("File successfully written: " + targetPath.toAbsolutePath());
            return true;

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
            return false;
        }
    }

    private String formatFileContent(String fileContent) {
        StringBuilder formattedContent = new StringBuilder();
        String[] entries = fileContent.split("\\) \\(");

        for (int i = 0; i < entries.length; i++) {
            String entry = entries[i].replace("(", "").replace(")", "").trim();
            String[] coordinates = entry.split(",");

            if (coordinates.length == 3) {
                formattedContent.append(String.join(",", coordinates)).append("\n");
            }
        }

        return formattedContent.toString();
    }
}