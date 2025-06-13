package Shodrone.Server;

import Shodrone.exceptions.FailedRequestException;
import core.Persistence.Application;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * MarshallerUnmarshaller class is responsible for parsing response messages
 * from the server and converting them into appropriate DTOs.
 * It checks for error messages and handles the parsing of user, customer,
 * show, proposal, and feedback responses.
 */
public class MarshallerUnmarshaller {

    private static final String REPORT_FOLDER = Application.settings().getReportPath();

    /**
     * Checks the response for error messages and throws an exception if an error is found.
     * @param response the response message from the server
     * @throws FailedRequestException if an error message is found in the response
     */
    private void checkForErrorMessage(final List<String> response) throws FailedRequestException {
        final String[] tokens = response.get(0).split(",");
        final String messageType = tokens[0].trim();

        if (messageType.equals("SERVER_ERROR") || messageType.equals("BAD_REQUEST")
                || messageType.equals("UNKNOWN_REQUEST") || messageType.equals("ERROR_IN_REQUEST")) {
            throw new FailedRequestException(messageType + ":" + tokens[tokens.length - 1].trim());
        }
    }

    public void verifyIfConfigWasEdited(List<String> response) throws FailedRequestException {
        checkForErrorMessage(response);

        if (response.isEmpty()) {
            throw new FailedRequestException("Empty response from server");
        }

        String[] tokens = response.get(0).split(",");
        String messageType = tokens[0].trim();

        if (!messageType.equals("CONFIG_EDITED") && !messageType.equals("CONFIG_EDIT_FAILED")) {
            throw new FailedRequestException("Unexpected response format: " + response.get(0));
        }

        if (messageType.equals("CONFIG_EDIT_FAILED")) {
            throw new FailedRequestException(tokens[1].trim().replace("\"", ""));
        }

        System.out.println(tokens[1].trim().replace("\"", ""));
    }

    private void verifyResponseFormat(List<String> response) throws FailedRequestException {
        if (response == null || response.isEmpty()) {
            throw new FailedRequestException("Response is null or empty");
        }
    }

    public void generateReportIfResponseIsCorrect(List<String> response) throws FailedRequestException {
        // Verify if the response is well-formed
        verifyResponseFormat(response);

        // Generate the report
        String reportPath = REPORT_FOLDER + "/simulation_report.txt";
        try {
            Files.write(Paths.get(reportPath), response, StandardCharsets.UTF_8);
            System.out.println("Report generated successfully at: " + reportPath);
        } catch (IOException e) {
            throw new FailedRequestException("Failed to generate report: " + e.getMessage());
        }
    }
}