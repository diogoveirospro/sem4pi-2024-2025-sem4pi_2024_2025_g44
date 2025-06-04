package Shodrone.Server;

import Shodrone.DTO.ShodroneUserDTO;
import Shodrone.exceptions.FailedRequestException;

import java.util.List;

public class MarshlerUnmarshler {
    public ShodroneUserDTO parseResponseMessageShodroneUser(List<String> response) throws FailedRequestException {
        checkForErrorMessage(response);

        if (response.size() < 2) {
            throw new IllegalArgumentException("Response does not contain enough data");
        }

        String[] data = response.get(1).split(",");
        for ( int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "").trim();
        }
        if (data.length != 3) {
            throw new IllegalArgumentException("Invalid response format. Expected 3 fields: email, username, phoneNumber");
        }

        String email = data[0].trim();
        String username = data[1].trim();
        String phoneNumber = data[2].trim();

        return new ShodroneUserDTO(email, username, phoneNumber);
    }

    private void checkForErrorMessage(final List<String> response) throws FailedRequestException {
        final String[] tokens = response.get(0).split(",");
        final String messageType = tokens[0].trim();

        if (messageType.equals("SERVER_ERROR") || messageType.equals("BAD_REQUEST")
                || messageType.equals("UNKNOWN_REQUEST") || messageType.equals("ERROR_IN_REQUEST")) {
            throw new FailedRequestException(messageType + ":" + tokens[tokens.length - 1].trim());
        }
    }
}