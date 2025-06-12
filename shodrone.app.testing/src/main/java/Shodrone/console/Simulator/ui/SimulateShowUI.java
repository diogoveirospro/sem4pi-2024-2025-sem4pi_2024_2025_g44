package Shodrone.console.Simulator.ui;

import Shodrone.console.Simulator.controller.SimulatorController;
import Shodrone.exceptions.UserCancelledException;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static shodrone.presentation.UtilsUI.readIntegerFromConsole;

public class SimulateShowUI extends AbstractFancyUI {

    private static final String PATH = "../sem4pi-2024-2025-sem4pi_2024_2025_g44/SCOMP/srcs";
    private static final String DEFAULT_INPUT_DIRECTORY = "DroneTests";
    private static final String DEFAULT_ABSOLUTE_INPUT_DIRECTORY = PATH + "/" + DEFAULT_INPUT_DIRECTORY;
    private static final String CONFIG_FILE_NAME = PATH + "/config.txt";
    private final SimulatorController controller = new SimulatorController();

    @Override
    protected boolean doShow() {
        boolean keepRunning = true;

        while (keepRunning) {
            String input_directory = chooseInputDirectory();
            int max_collisions = enterValidMaxCollisions();
            int num_drones = enterValidNumDrones();
            int drone_radius = enterValidDroneRadius();
            int x_max = enterValidXMax();
            int y_max = enterValidYMax();
            int z_max = enterValidZMax();
            int time_step = enterValidTimeStep();

            try {
                controller.editConfigFile(CONFIG_FILE_NAME, input_directory, max_collisions, num_drones,
                        drone_radius, x_max, y_max, z_max, time_step);
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nConfiguration file successfully updated!" + UtilsUI.RESET);
            } catch (Exception e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError writing to config file: " + e.getMessage() + UtilsUI.RESET);
                return false;
            }

            System.out.println(UtilsUI.BOLD + UtilsUI.YELLOW + "\nThese were the selected configurations:" + UtilsUI.RESET);
            System.out.println(UtilsUI.BOLD + "INPUT_DIR = " + UtilsUI.RESET + input_directory);
            System.out.println(UtilsUI.BOLD + "MAX_COLLISIONS = " + UtilsUI.RESET + max_collisions);
            System.out.println(UtilsUI.BOLD + "NUM_DRONES = " + UtilsUI.RESET + num_drones);
            System.out.println(UtilsUI.BOLD + "DRONE_RADIUS = " + UtilsUI.RESET + drone_radius);
            System.out.println(UtilsUI.BOLD + "X_MAX = " + UtilsUI.RESET + x_max);
            System.out.println(UtilsUI.BOLD + "Y_MAX = " + UtilsUI.RESET + y_max);
            System.out.println(UtilsUI.BOLD + "Z_MAX = " + UtilsUI.RESET + z_max);
            System.out.println(UtilsUI.BOLD + "TIME_STEP = " + UtilsUI.RESET + time_step);

            System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + "\n-> You will be redirected to the simulator in 3 seconds..." + UtilsUI.RESET);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            UtilsUI.openCodeInC(PATH);

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
        return "Simulate a Show";
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
                UtilsUI.BOLD + UtilsUI.BLUE + "\n\nChoose a Folder for the Input Directory:\n" + UtilsUI.RESET,
                displayFolders, (s) -> System.out.print(s)
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
                return "./" + DEFAULT_INPUT_DIRECTORY + "/" + folders.get(option);
            }

        } while (true);
    }

    private int enterValidMaxCollisions() {
        int max_collisions;
        do {
            try {
                max_collisions = readIntegerFromConsole(UtilsUI.BOLD + "Enter a max collisions " +
                        "(or type -1 to go back): " + UtilsUI.RESET);

                if (max_collisions == -1) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
                }

                if (max_collisions < 0) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nMax collisions must be a non-negative integer." + UtilsUI.RESET);
                    continue;
                }

                return max_collisions;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                continue;
            }
        } while (true);
    }

    private int enterValidNumDrones() {
        int num_drones;
        do {
            try {
                num_drones = readIntegerFromConsole(UtilsUI.BOLD + "Enter the number of drones " +
                        "(or type -1 to go back): " + UtilsUI.RESET);

                if (num_drones == -1) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
                }

                if (num_drones <= 0) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNumber of drones must be a positive integer." + UtilsUI.RESET);
                    continue;
                }

                return num_drones;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                continue;
            }
        } while (true);
    }

    private int enterValidDroneRadius() {
        int drone_radius;
        do {
            try {
                drone_radius = readIntegerFromConsole(UtilsUI.BOLD + "Enter the drone radius " +
                        "(or type -1 to go back): " + UtilsUI.RESET);

                if (drone_radius == -1) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
                }

                if (drone_radius <= 0) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nDrone radius must be a positive integer." + UtilsUI.RESET);
                    continue;
                }

                return drone_radius;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                continue;
            }
        } while (true);
    }

    private int enterValidXMax() {
        int x_max;
        do {
            try {
                x_max = readIntegerFromConsole(UtilsUI.BOLD + "Enter the maximum X coordinate " +
                        "(or type -1 to go back): " + UtilsUI.RESET);

                if (x_max == -1) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
                }

                if (x_max <= 0) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nX max must be a positive integer." + UtilsUI.RESET);
                    continue;
                }

                return x_max;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                continue;
            }
        } while (true);
    }

    private int enterValidYMax() {
        int y_max;
        do {
            try {
                y_max = readIntegerFromConsole(UtilsUI.BOLD + "Enter the maximum Y coordinate " +
                        "(or type -1 to go back): " + UtilsUI.RESET);

                if (y_max == -1) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
                }

                if (y_max <= 0) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nY max must be a positive integer." + UtilsUI.RESET);
                    continue;
                }

                return y_max;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                continue;
            }
        } while (true);
    }

    private int enterValidZMax() {
        int z_max;
        do {
            try {
                z_max = readIntegerFromConsole(UtilsUI.BOLD + "Enter the maximum Z coordinate " +
                        "(or type -1 to go back): " + UtilsUI.RESET);

                if (z_max == -1) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
                }

                if (z_max <= 0) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nZ max must be a positive integer." + UtilsUI.RESET);
                    continue;
                }

                return z_max;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                continue;
            }
        } while (true);
    }

    private int enterValidTimeStep() {
        int time_step;
        do {
            try {
                time_step = readIntegerFromConsole(UtilsUI.BOLD + "Enter the time step in milliseconds " +
                        "(or type -1 to go back): " + UtilsUI.RESET);

                if (time_step == -1) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
                }

                if (time_step <= 0) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nTime step must be a positive integer." + UtilsUI.RESET);
                    continue;
                }

                return time_step;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                continue;
            }
        } while (true);
    }
}
