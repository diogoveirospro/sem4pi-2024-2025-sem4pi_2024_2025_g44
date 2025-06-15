package Shodrone.console.Drone.ui;

import Shodrone.console.Drone.controller.DroneRunnerController;
import Shodrone.exceptions.UserCancelledException;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DroneRunnerUI extends AbstractFancyUI {


    private static final String PATH = "../sem4pi-2024-2025-sem4pi_2024_2025_g44/SCOMP/srcs";
    private static final String DEFAULT_INPUT_DIRECTORY = "DroneTests";
    private static final String DEFAULT_ABSOLUTE_INPUT_DIRECTORY = PATH + "/" + DEFAULT_INPUT_DIRECTORY;
    private final DroneRunnerController controller = new DroneRunnerController();

    @Override
    protected boolean doShow() {
        boolean keepRunning = true;

        while (keepRunning) {
            String inputDirectory = chooseInputDirectory();
            System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + "\nYou chose the directory: " + inputDirectory + UtilsUI.RESET);
            if (inputDirectory == null) {
                continue;
            }

            boolean editExistingFile = UtilsUI.confirm(UtilsUI.BOLD + UtilsUI.YELLOW + "\nDo you want to edit an existing file? (y/n): " + UtilsUI.RESET);

            if (editExistingFile) {
                System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + "\nFile structure:\n" + UtilsUI.RESET);
                System.out.println("X,Y,Z\nVX1,VY1,VZ1\nVX2,VY2,VZ2\n...\nVXN,VYN,VZN\n");
                System.out.println(UtilsUI.YELLOW + "X, Y, Z is the initial position.\nVXN, VYN, VZN are the vectors." + UtilsUI.RESET);
                chooseFileInDirectory(inputDirectory);
            } else {
                System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + "\nFile structure:\n" + UtilsUI.RESET);
                System.out.println("X,Y,Z\nVX1,VY1,VZ1\nVX2,VY2,VZ2\n...\nVXN,VYN,VZN\n");
                System.out.println(UtilsUI.YELLOW + "X, Y, Z is the initial position.\nVXN, VYN, VZN are the vectors." + UtilsUI.RESET);
                createNewFile(inputDirectory);
            }

            boolean confirm = UtilsUI.confirm(UtilsUI.BOLD + UtilsUI.YELLOW + "\nDo you want to return to the main menu? (y/n): " + UtilsUI.RESET);
            if (confirm) {
                keepRunning = false;
                System.out.println(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Configure Drone";
    }

    private void chooseFileInDirectory(String directoryPath) {
        List<String> files = new ArrayList<>();
        Path dirPath = Paths.get(directoryPath);

        try {
            Files.list(dirPath)
                    .filter(Files::isRegularFile)
                    .forEach(file -> files.add(file.getFileName().toString()));
        } catch (IOException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError Reading Files: " + e.getMessage() + UtilsUI.RESET);
            return;
        }

        if (files.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo Files Found." + UtilsUI.RESET);
            return;
        }

        ListWidget<String> fileListWidget = new ListWidget<>(
                UtilsUI.BOLD + UtilsUI.BLUE + "\n\nChoose a File to edit:\n" + UtilsUI.RESET,
                files, System.out::print
        );
        fileListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(files);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled." + UtilsUI.RESET);
            }

            if (option < 0 || option >= files.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                String filePath = directoryPath + "/" + files.get(option);

                boolean eliminateDrone = UtilsUI.confirm(UtilsUI.BOLD + UtilsUI.YELLOW + "\nDo you want to eliminate this drone? (y/n): " + UtilsUI.RESET);
                if (eliminateDrone) {
                    try {
                        Files.delete(Paths.get(filePath));
                        controller.sendFileToServer(filePath);
                        System.out.println(UtilsUI.BOLD + UtilsUI.GREEN + "\nDrone file deleted successfully.\n" + UtilsUI.RESET);
                    } catch (IOException e) {
                        System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError Deleting Drone File: " + e.getMessage() + UtilsUI.RESET);
                    }
                } else {
                    checkFileChangesAndSend(filePath);
                }
                return;
            }
        } while (true);
    }


    private void createNewFile(String directoryPath) {
        try {
            Path dirPath = Paths.get(directoryPath).toAbsolutePath().normalize();
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            List<String> files = new ArrayList<>();
            Files.list(dirPath)
                    .filter(Files::isRegularFile)
                    .forEach(file -> files.add(file.getFileName().toString()));

            int nextNumber = files.stream()
                    .filter(name -> name.startsWith("file_drone_") && name.endsWith(".txt"))
                    .map(name -> name.replace("file_drone_", "").replace(".txt", ""))
                    .mapToInt(Integer::parseInt)
                    .max()
                    .orElse(0) + 1;

            String newFileName = "file_drone_" + nextNumber + ".txt";
            Path newFilePath = dirPath.resolve(newFileName).normalize();

            Files.createFile(newFilePath);
            System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + "\nFile created successfully: " + newFilePath.toRealPath() + UtilsUI.RESET);
            checkFileChangesAndSend(newFilePath.toString());
        } catch (IOException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError Creating File: " + e.getMessage() + UtilsUI.RESET);
        }
    }

    private String chooseInputDirectory() {
        List<String> folders = new ArrayList<>();
        List<String> displayFolders = new ArrayList<>();
        Path basePath = Paths.get(DEFAULT_ABSOLUTE_INPUT_DIRECTORY);

        try {
            Files.list(basePath)
                    .filter(Files::isDirectory)
                    .forEach(dir -> {
                        String folderName = dir.getFileName().toString();
                        folders.add(folderName);
                        try {
                            long count = Files.list(dir).filter(Files::isRegularFile).count();
                            displayFolders.add(folderName + " - " + count + " drones");
                        } catch (IOException e) {
                            displayFolders.add(folderName + " - error");
                        }
                    });
        } catch (IOException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError Reading Folders: " + e.getMessage() + UtilsUI.RESET);
            return null;
        }

        if (folders.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo Folders Found." + UtilsUI.RESET);
            return null;
        }

        ListWidget<String> folderListWidget = new ListWidget<>(
                UtilsUI.BOLD + UtilsUI.BLUE + "\n\nChoose a Folder to change or add the drone:\n" + UtilsUI.RESET,
                displayFolders, System.out::print
        );
        folderListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(displayFolders);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled." + UtilsUI.RESET);
            }

            if (option < 0 || option >= folders.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return "./" + DEFAULT_ABSOLUTE_INPUT_DIRECTORY + "/" + folders.get(option);
            }

        } while (true);
    }

    private void checkFileChangesAndSend(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFile does not exist: " + filePath + UtilsUI.RESET);
                return;
            }

            List<String> previousContent = Files.exists(path) ? Files.readAllLines(path, StandardCharsets.UTF_8) : new ArrayList<>();


            boolean confirm = false;
            while (!confirm) {
                try {
                    UtilsUI.openFileForEditing(filePath);
                    System.out.println(UtilsUI.BOLD + UtilsUI.YELLOW + "\nWaiting for the customer to close the file...\n" + UtilsUI.RESET);
                    confirm = UtilsUI.confirm(UtilsUI.BOLD + UtilsUI.YELLOW + "\nDo you want to continue (y/n): " + UtilsUI.RESET);
                    Thread.sleep(1000);
                    if (!isFileFormatValid(filePath)) {
                        confirm = false;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
                }
            }
            List<String> updatedContent = Files.readAllLines(path, StandardCharsets.UTF_8);

            if (updatedContent.isEmpty()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFile is empty. Removing file: " + filePath + UtilsUI.RESET);
                Files.delete(path);
                return;
            }

            if (!previousContent.equals(updatedContent)) {
                System.out.println(UtilsUI.BOLD + UtilsUI.GREEN + "\nFile has been modified. Sending to server...\n" + UtilsUI.RESET);
                controller.sendFileToServer(filePath);
            } else {
                System.out.println(UtilsUI.BOLD + UtilsUI.YELLOW + "\nNo changes detected in the file.\n" + UtilsUI.RESET);
            }
        } catch (IOException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError Checking File Changes: " + e.getMessage() + UtilsUI.RESET);
        }
    }

    private boolean isFileFormatValid(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);

            if (lines.isEmpty()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFile is empty: " + filePath + UtilsUI.RESET);
                return false;
            }

            if (!lines.get(0).matches("\\d+,\\d+,\\d+")) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid format in the first line: " + lines.get(0) + UtilsUI.RESET);
                return false;
            }

            for (int i = 1; i < lines.size(); i++) {
                if (!lines.get(i).matches("\\d+,\\d+,\\d+")) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid format in line " + (i + 1) + ": " + lines.get(i) + UtilsUI.RESET);
                    return false;
                }
            }

            System.out.println(UtilsUI.BOLD + UtilsUI.GREEN + "\nFile format is valid: " + filePath + UtilsUI.RESET);
            return true;

        } catch (IOException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError Reading File: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

}
