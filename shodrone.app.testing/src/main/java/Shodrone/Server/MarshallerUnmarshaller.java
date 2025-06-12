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

    /**
     * Splits a line into tokens, respecting quoted strings.
     * @param line the line to split
     * @return a list of tokens
     */
    private List<String> splitRespectingQuotes(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        boolean insideQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '\"') {
                insideQuotes = !insideQuotes;
            } else if (c == ',' && !insideQuotes) {
                tokens.add(currentToken.toString().trim());
                currentToken.setLength(0);
            } else {
                currentToken.append(c);
            }
        }

        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString().trim());
        }

        return tokens;
    }

    public void verifyIfConfigWasEdited(List<String> response) throws FailedRequestException {
        checkForErrorMessage(response);

        if (response.isEmpty()) {
            throw new FailedRequestException("Empty response from server");
        }

        String result = response.get(0).trim();
        if (!result.equalsIgnoreCase("true") && !result.equalsIgnoreCase("false")) {
            throw new FailedRequestException("Unexpected response format: " + result);
        }

        if (result.equalsIgnoreCase("false")) {
            throw new FailedRequestException("Configuration edit failed");
        }

        System.out.println( "Configuration was successfully edited.");
    }
}