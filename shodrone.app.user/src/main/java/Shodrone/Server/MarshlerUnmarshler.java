package Shodrone.Server;

import Shodrone.DTO.CustomerDTO;
import Shodrone.DTO.ShodroneUserDTO;
import Shodrone.DTO.ShowDTO;
import Shodrone.exceptions.FailedRequestException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public CustomerDTO parseResponseMessageCustomer(List<String> response) throws FailedRequestException {
        checkForErrorMessage(response);

        if (response.size() < 2) {
            throw new IllegalArgumentException("Response does not contain enough data");
        }

        String[] data = response.get(1).split(",");
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "").trim();
        }

        if (data.length < 2) {
            throw new IllegalArgumentException("Invalid response format. Expected at least 2 fields: companyName, VatNumber");
        }

        String companyName = data[0];
        String vatNumber = data[1];

        return new CustomerDTO(companyName, vatNumber);
    }

    public Iterable<ShowDTO> parseResponseMessageShow(List<String> response) throws FailedRequestException {
        checkForErrorMessage(response);

        List<ShowDTO> shows = new ArrayList<>();

        response.remove(0);

        for (String line : response) {
            List<String> tokens = splitRespectingQuotes(line);

            if (tokens.size() < 12) {
                throw new IllegalArgumentException("Invalid response format. Expected 12 fields.");
            }

            String requestNumber = tokens.get(0);
            String proposalNumber = tokens.get(1);
            Map<String, List<String>> droneConfiguration = parseDroneConfiguration(tokens.get(2));
            String video = tokens.get(3);
            List<String> figuresConfiguration = parseFiguresConfiguration(tokens.get(4));
            String showDescription = tokens.get(5);
            String showLocation = tokens.get(6);
            String showDate = tokens.get(7);
            String showTime = tokens.get(8);
            Long quantityOfDrones = Long.parseLong(tokens.get(9));
            String insurance = tokens.get(10);
            String showDuration = tokens.get(11);

            shows.add(new ShowDTO(requestNumber, proposalNumber, droneConfiguration, video,
                    figuresConfiguration, showDescription, showLocation, showDate, showTime,
                    quantityOfDrones, insurance, showDuration));
        }

        return shows;
    }

    private Map<String, List<String>> parseDroneConfiguration(String token) {
        Map<String, List<String>> droneConfig = new HashMap<>();
        String[] models = token.split(";");

        for (String modelEntry : models) {
            String[] parts = modelEntry.split(":");
            if (parts.length == 2) {
                String modelName = parts[0].trim();
                String[] drones = parts[1].split(",");
                List<String> droneList = new ArrayList<>();
                for (String drone : drones) {
                    droneList.add(drone.trim());
                }
                droneConfig.put(modelName, droneList);
            }
        }
        return droneConfig;
    }

    private List<String> parseFiguresConfiguration(String token) {
        String[] figures = token.split(",");
        List<String> figureList = new ArrayList<>();
        for (String figure : figures) {
            figureList.add(figure.trim());
        }
        return figureList;
    }

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
}