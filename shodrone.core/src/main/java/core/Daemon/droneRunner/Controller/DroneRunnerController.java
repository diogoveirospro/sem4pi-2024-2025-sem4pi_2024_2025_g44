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

            Path baseDir = Paths.get("").toAbsolutePath();
            Path targetPath = baseDir.resolve(filePath).normalize();

            Files.createDirectories(targetPath.getParent());

            String formattedContent = formatFileContent(fileContent);

            Files.write(targetPath, formattedContent.getBytes(StandardCharsets.UTF_8));

            System.out.println("File successfully written: " + targetPath.toAbsolutePath());
            return true;

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
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
                if (i == 0) {
                    formattedContent.append(String.join(", ", coordinates)).append("\n");
                } else {
                    formattedContent.append(String.join(", ", coordinates)).append("\n");
                }
            }
        }

        return formattedContent.toString();
    }
}