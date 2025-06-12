package Shodrone.Server;

import Shodrone.exceptions.FailedRequestException;

import java.util.ArrayList;
import java.util.List;

/**
 * MarshallerUnmarshaller class is responsible for parsing response messages
 * from the server and converting them into appropriate DTOs.
 * It checks for error messages and handles the parsing of user, customer,
 * show, proposal, and feedback responses.
 */
public class MarshallerUnmarshaller {

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
}