package shodrone.presentation;

import eapli.framework.strings.util.StringPredicates;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UtilsUI {

    // Reset & Estilos
    public static final String RESET = "\033[0m";
    public static final String BOLD = "\033[1m";
    public static final String UNDERLINE = "\033[4m";
    public static final String REVERSED = "\033[7m";

    // Cores de Texto
    public static final String BLACK = "\033[30m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String PURPLE = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";

    // Cores de Fundo
    public static final String BG_BLACK = "\033[40m";
    public static final String BG_RED = "\033[41m";
    public static final String BG_GREEN = "\033[42m";
    public static final String BG_YELLOW = "\033[43m";
    public static final String BG_BLUE = "\033[44m";
    public static final String BG_PURPLE = "\033[45m";
    public static final String BG_CYAN = "\033[46m";
    public static final String BG_WHITE = "\033[47m";

    // Cores de Texto Brilhantes
    public static final String BRIGHT_BLACK = "\033[90m";
    public static final String BRIGHT_RED = "\033[91m";
    public static final String BRIGHT_GREEN = "\033[92m";
    public static final String BRIGHT_YELLOW = "\033[93m";
    public static final String BRIGHT_BLUE = "\033[94m";
    public static final String BRIGHT_PURPLE = "\033[95m";
    public static final String BRIGHT_CYAN = "\033[96m";
    public static final String BRIGHT_WHITE = "\033[97m";

    // Cores de Fundo Brilhantes
    public static final String BG_BRIGHT_BLACK = "\033[100m";
    public static final String BG_BRIGHT_RED = "\033[101m";
    public static final String BG_BRIGHT_GREEN = "\033[102m";
    public static final String BG_BRIGHT_YELLOW = "\033[103m";
    public static final String BG_BRIGHT_BLUE = "\033[104m";
    public static final String BG_BRIGHT_PURPLE = "\033[105m";
    public static final String BG_BRIGHT_CYAN = "\033[106m";
    public static final String BG_BRIGHT_WHITE = "\033[107m";


    /**
     * Generates a formatted header for the console.
     *
     * @param message the message to display in the header
     * @return the formatted header string
     * @author Diogo Pereira
     */
    public static String generateHeader(String color, String message) {
        int width = 60;
        clearConsole();
        String border = "=".repeat(width);
        int padding = (width - message.length()) / 2;
        String paddedMessage = " ".repeat(Math.max(0, padding)) + message;

        return color + BOLD + border + "\n" + paddedMessage + "\n" + border + RESET;
    }


    /**
     * Reads a line from the console
     *
     * @param prompt The prompt to show to the user
     * @return The line read from the console
     */
    static public String readLineFromConsole(String prompt) {
        try {
            System.out.print("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads a line from the console
     * @param prompt The prompt to show to the user
     * @param message The message to show to the user if the input is empty
     * @return The line read from the console
     */
    public static String readNonEmptyLine(final String prompt, final String message) {
        while (true) {
            String text = readLineFromConsole(prompt);
            if (!StringPredicates.isNullOrEmpty(text)) {
                return text;
            }

            System.out.println(message);
        }
    }

    /**
     * Reads a line from the console
     *
     * @param prompt The prompt to show to the user
     * @return The line read from the console
     */
    static public int readIntegerFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);
                int value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException ex) {
                System.out.println(RED + BOLD + "Invalid input. Please enter a valid integer." + RESET);
            }
        } while (true);
    }

    /**
     * Reads a line from the console
     *
     * @param prompt The prompt to show to the user
     * @return The line read from the console
     */
    static public double readDoubleFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                assert input != null;
                input = input.replace(',', '.');

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex) {
                System.out.println(RED + BOLD + "Invalid input. Please enter a valid decimal number." + RESET);
            }
        } while (true);
    }

    /**
     * Reads a line from the console
     *
     * @param prompt The prompt to show to the user
     * @return The line read from the console
     */
    static public Date readDateFromConsole(String prompt) {
        do {
            try {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex) {
                System.out.println(BOLD + RED + "\nInvalid date format. Please use dd-MM-yyyy." + RESET);
            }
        } while (true);
    }

    /**
     * Reads a line from the console
     *
     * @param message The prompt to show to the user
     * @return The line read from the console
     */
    static public boolean confirm(String message) {
        String input;
        do {
            input = UtilsUI.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("y");
    }

    /**
     * Reads a line from the console
     *
     * @param list The list to show to the user
     * @param header The header to show to the user
     * @return The object selected by the user
     */
    static public Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    /**
     * Reads a line from the console
     *
     * @param list The list to show to the user
     * @param header The header to show to the user
     * @return The index selected by the user
     */
    static public int showAndSelectIndex(List list, String header) {
        clearConsole();
        showList(list, header);
        return selectsIndex(list);
    }

    /**
     * Reads a line from the console
     *
     * @param list The list to show to the user
     * @param header The header to show to the user
     */
    static public void showList(List list, String header) {
        System.out.println(header);

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println("  " + index + " - " + o.toString());
        }
        //System.out.println();
        System.out.println("  0 - Exit");
    }

    /**
     * Reads a line from the console
     *
     * @param list The list to show to the user
     * @return The object selected by the user
     */
    static public Object selectsObject(List list) {
        String input;
        int value;
        do {
            input = UtilsUI.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }

    /**
     * Reads a line from the console
     *
     * @param list The list to show to the user
     * @return The index selected by the user
     */
    static public int selectsIndex(List list) {
        String input;
        int value;
        do {
            input = UtilsUI.readLineFromConsole("Type your option (or type '0' to go back): ");

            try {
                value = Integer.valueOf(input);

                if (value == 0) {
                    return -2;
                }

                if (value < 0 || value > list.size()) {
                    return -1;
                }

            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    /**
     * Reads a line from the console with Different prompt
     *
     * @param list The list to show to the user
     * @return The index selected by the user
     */
    static public int selectsIndexWithDifferentPrompt(List list, String prompt) {
        String input;
        int value;
        do {
            input = UtilsUI.readLineFromConsole(prompt);

            try {
                value = Integer.valueOf(input);

                if (value == 0) {
                    return -2;
                }

                if (value < 0 || value > list.size()) {
                    return -1;
                }

            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    /**
     * Reads a line from the console
     */
    static public void goBackAndWait() {
        String input;
        do {
            input = UtilsUI.readLineFromConsole("Press '0' to go back: ");
        } while (!Objects.equals(input, "0"));
    }

    /**
     * Opens the specified file in the default browser.
     *
     * @param file The file to open.
     * @author Diogo Pereira
     */
    public static void openInBrowser(File file) {
        try {
            if (!file.exists()) {
                System.err.println("File does not exist: " + file.getAbsolutePath());
                return;
            }

            String os = System.getProperty("os.name").toLowerCase();
            String filePath = file.getAbsolutePath();

            if (os.contains("win")) {
                // Windows: uses the ‘start’ command for the default browser
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "chrome", filePath});
            } else if (os.contains("mac")) {
                // MacOS: uses the ‘open’ command for the default browser
                Runtime.getRuntime().exec(new String[]{"open", "-a", "Google Chrome", filePath});
            } else if (os.contains("nix") || os.contains("nux")) {
                // Linux: uses the ‘xdg-open’ command for the default browser
                Runtime.getRuntime().exec(new String[]{"xdg-open", filePath});
            } else {
                System.err.println("Unsupported OS: " + os);
            }
        } catch (IOException e) {
            System.err.println("Error opening file in the browser: " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }

    /**
     * Opens the specified file in Microsoft Excel, Notepad, or the default text editor.
     *
     * @param file The file to open.
     * @author Diogo Pereira
     */
    public static void openInExcel(File file) {
        try {
            if (!file.exists()) {
                System.err.println("File does not exist: " + file.getAbsolutePath());
                return;
            }

            String os = System.getProperty("os.name").toLowerCase();
            String filePath = file.getAbsolutePath();

            if (os.contains("win")) {
                // Windows: try to open with Excel
                try {
                    // Verify if Excel is installed
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "excel", filePath});
                } catch (IOException e) {
                    // If it fails, open with Notepad
                    System.err.println("Excel not found, opening with Notepad...");
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "notepad", filePath});
                }
            } else if (os.contains("mac")) {
                // MacOS: try to open with Excel
                try {
                    Runtime.getRuntime().exec(new String[]{"open", "-a", "Microsoft Excel", filePath});
                } catch (IOException e) {
                    // If it fails, open with the default editor
                    System.err.println("Excel not found, opening with default editor...");
                    Runtime.getRuntime().exec(new String[]{"open", filePath});
                }
            } else if (os.contains("nix") || os.contains("nux")) {
                // Linux: try to open with the default text editor
                try {
                    Runtime.getRuntime().exec(new String[]{"xdg-open", filePath});
                } catch (IOException e) {
                    // If it fails, open with the default text editor
                    System.err.println("Error opening file, opening with default text editor...");
                    Runtime.getRuntime().exec(new String[]{"xdg-open", filePath});
                }
            } else {
                System.err.println("Unsupported OS: " + os);
            }
        } catch (IOException e) {
            System.err.println("Error opening file in the editor: " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }

    /**
     * Opens the specified directory in the Windows Subsystem for Linux (WSL).
     *
     * @param path The path to the directory to open in WSL.
     * @author Diogo Pereira
     */
    public static void openCodeInC(String path) {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // Windows: try to open with WSL
            try {
                // Resolve working directory dynamically
                String projectDir = System.getProperty("user.dir");
                File workingDir = new File(projectDir, path);
                if (!workingDir.exists()) {
                    System.err.println("Directory does not exist: " + workingDir.getAbsolutePath());
                    return;
                }
                String wslWorkingDir = workingDir.getCanonicalPath().replace("\\", "/").replace("C:", "/mnt/c");

                // Escape quotes within the WSL command for Windows Terminal
                String command = String.format("bash -c \"cd '%s' && make run\"", wslWorkingDir);

                // Command to open Windows Terminal and execute the WSL command
                String terminalCommand = String.format("wt wsl %s", command);

                // Launch Windows Terminal with the command
                ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", terminalCommand);
                processBuilder.start();  // Start the process (opens a new terminal window with Windows Terminal)

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (os.contains("mac")) {
            // MacOS: try to open with terminal
            try {
                String projectDir = System.getProperty("user.dir");
                File workingDir = new File(projectDir, path);
                if (!workingDir.exists()) {
                    System.err.println("Directory does not exist: " + workingDir.getAbsolutePath());
                    return;
                }

                String command = String.format("cd '%s' && make run", workingDir.getCanonicalPath());

                // Command to open Terminal and execute the make command
                String[] terminalCommand = { "osascript", "-e",
                        String.format("tell application \"Terminal\" to do script \"%s\"", command) };
                ProcessBuilder processBuilder = new ProcessBuilder(terminalCommand);
                processBuilder.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (os.contains("nix") || os.contains("nux")) {
            // Linux: try to open with gnome-terminal
            try {
                String projectDir = System.getProperty("user.dir");
                File workingDir = new File(projectDir, path);
                if (!workingDir.exists()) {
                    System.err.println("Directory does not exist: " + workingDir.getAbsolutePath());
                    return;
                }

                String command = String.format("cd '%s' && make run", workingDir.getCanonicalPath());

                // Command to open gnome-terminal and execute the make command
                String[] terminalCommand = { "gnome-terminal", "--", "bash", "-c", command };
                ProcessBuilder processBuilder = new ProcessBuilder(terminalCommand);
                processBuilder.start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.err.println("Unsupported OS: " + os);
        }
    }

    public static void openInNotepad(File file) {
        try {
            // Check if the file exists
            if (!file.exists()) {
                System.err.println("File does not exist: " + file.getAbsolutePath());
                return;
            }

            String os = System.getProperty("os.name").toLowerCase();
            String filePath = file.getAbsolutePath();

            if (os.contains("win")) {
                // Windows: open with Notepad (Bloco de Notas)
                new ProcessBuilder("notepad.exe", filePath).start();
            } else if (os.contains("mac")) {
                // MacOS: open with the default text editor
                new ProcessBuilder("open", filePath).start();
            } else if (os.contains("nix") || os.contains("nux")) {
                // Linux: open with the default text editor
                new ProcessBuilder("xdg-open", filePath).start();
            } else {
                System.err.println("Unsupported OS: " + os);
            }
        } catch (IOException e) {
            System.err.println("Error opening file in Notepad: " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }

    /**
     * Validates a date string in the format "dd-MM-yyyy".
     * @param date The date string to validate.
     * @return True if the date is valid, false otherwise.
     *
     * @author Diogo Pereira
     */
    public static boolean isValidDateFormat1(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);

        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * Checks if the start date is before the end date.
     * @param startDate The start date.
     * @param endDate The end date.
     * @return True if the start date is before the end date, false otherwise.
     *
     * @author Diogo Pereira
     */
    public static boolean isBeforeDate(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);

            return start.after(end);
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * This method is responsible for clearing the console.
     *
     * @author Diogo Pereira
     */
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Reads a password from the console.
     * @param prompt The prompt to show to the user
     * @return The password read from the console
     */
    public static String readPassword(String prompt) {
        Console console = System.console();
        if (console != null) {
            char[] passwordChars = console.readPassword("%s", prompt);
            return new String(passwordChars);
        } else {
            System.out.println(RED + BOLD + "WARNING: Cannot mask password in this environment." + RESET);
            return readLineFromConsole(prompt);
        }
    }
    public static int readInt(String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

}