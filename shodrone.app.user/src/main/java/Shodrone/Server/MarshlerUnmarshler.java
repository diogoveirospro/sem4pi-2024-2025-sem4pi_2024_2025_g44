package Shodrone.Server;

import Shodrone.DTO.ShodroneUserDTO;
import Shodrone.exceptions.FailedRequestException;

import java.util.List;

public class MarshlerUnmarshler {
    public ShodroneUserDTO parseResponseMessageShodroneUser(List<String> response) throws FailedRequestException {
        checkForErrorMessage( response);

        if (response.isEmpty()) {
            throw new IllegalArgumentException("Response is empty");
        }

        String[] data = response.get(0).split(";");
        if (data.length != 3) {
            throw new IllegalArgumentException("Invalid response format. Expected 3 fields: email, username, phoneNumber");
        }

        String email = data[0];
        String username = data[1];
        String phoneNumber = data[2];

        return new ShodroneUserDTO(email, username, phoneNumber);
    }

    private void checkForErrorMessage(final List<String> response) throws FailedRequestException {
        final String[] tokens = response.get(0).split(",");
        final String messageType = tokens[0];

        if (messageType.equals("SERVER_ERROR") || messageType.equals("BAD_REQUEST")
                || messageType.equals("UNKNOWN_REQUEST") || messageType.equals("ERROR_IN_REQUEST")) {
            throw new FailedRequestException(messageType + ":" + tokens[tokens.length - 1]);
        }
    }
}