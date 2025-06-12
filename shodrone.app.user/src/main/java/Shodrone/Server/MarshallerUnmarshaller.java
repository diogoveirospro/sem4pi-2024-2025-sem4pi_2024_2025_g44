package Shodrone.Server;

import Shodrone.DTO.*;
import Shodrone.exceptions.FailedRequestException;
import core.ProposalDeliveryInfo.domain.ValueObjects.ProposalDeliveryInfoCode;
import core.ShowProposal.domain.Entities.ShowProposal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MarshallerUnmarshaller class is responsible for parsing response messages
 * from the server and converting them into appropriate DTOs.
 * It checks for error messages and handles the parsing of user, customer,
 * show, proposal, and feedback responses.
 */
public class MarshallerUnmarshaller {

    /**
     * Parses the response message for a Shodrone user.
     * @param response the response message from the server
     * @return ShodroneUserDTO containing user details
     * @throws FailedRequestException if the response contains an error message
     */
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
     * Parses the response message for a customer.
     * @param response the response message from the server
     * @return CustomerDTO containing customer details
     * @throws FailedRequestException if the response contains an error message
     */
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

    /**
     * Parses the response message for shows.
     * @param response the response message from the server
     * @return an iterable of ShowDTO containing show details
     * @throws FailedRequestException if the response contains an error message
     */
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

    /**
     * Parses the drone configuration from a token.
     * @param token the token containing drone configuration
     * @return a map of drone models and their corresponding drones
     */
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

    /**
     * Parses the figures configuration from a token.
     * @param token the token containing figures configuration
     * @return a list of figures
     */
    private List<String> parseFiguresConfiguration(String token) {
        String[] figures = token.split(",");
        List<String> figureList = new ArrayList<>();
        for (String figure : figures) {
            figureList.add(figure.trim());
        }
        return figureList;
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

    /**
     * Parses the response message for show proposals.
     * @param response the response message from the server
     * @return an iterable of ShowProposalDTO containing proposal details
     * @throws FailedRequestException if the response contains an error message
     */
    public Iterable<ShowProposalDTO> parseResponseMessageProposal(List<String> response) throws FailedRequestException {
        checkForErrorMessage(response);

        List<ShowProposalDTO> proposals = new ArrayList<>();

        response.remove(0);

        for (String line : response) {
            List<String> tokens = splitRespectingQuotes(line);

            if (tokens.size() < 5) {
                throw new IllegalArgumentException("Invalid response format. Expected 4 fields.");
            }

            String proposalNumber = tokens.get(0);
            String showDate = tokens.get(1);
            String showTime = tokens.get(2);
            String showDuration = tokens.get(3);
            String showLocation = tokens.get(4);

            proposals.add(new ShowProposalDTO(proposalNumber, showDate, showTime, showDuration, showLocation));
        }

        return proposals;
    }

    /**
     * Parses the response message for feedback on a proposal.
     * @param response the response message from the server
     * @return true if the proposal was accepted or rejected, false otherwise
     * @throws FailedRequestException if the response contains an error message
     */
    public boolean parseResponseMessageFeedback(List<String> response) throws FailedRequestException {
        checkForErrorMessage(response);

        response.remove(0);

        for (String line : response) {
            List<String> tokens = splitRespectingQuotes(line);

            if (tokens.size() < 3) {
                throw new IllegalArgumentException("Invalid response format. Expected 4 fields.");
            }
            String decision = tokens.get(1);

            return decision.equalsIgnoreCase("ACCEPTED") || decision.equalsIgnoreCase("REJECTED");
        }

        return false;
    }

    public ProposalDeliveryInfoCode parseResponseMessageProposalDeliveryInfoCode(List<String> response) throws FailedRequestException {
        checkForErrorMessage(response);

        if (response.size() < 2) {
            throw new IllegalArgumentException("Invalid response format. Expected at least 2 lines.");
        }

        String filePath = response.get(1); // a segunda linha é o path do ficheiro

        return new ProposalDeliveryInfoCode(filePath);
    }

    public ShowProposalDTO parseResponseMessageProposalByCode(List<String> response) throws FailedRequestException {
            response.remove(0);
            List<String> tokens = splitRespectingQuotes(response.get(1));

            if (tokens.size() < 4) {
                throw new IllegalArgumentException("Invalid response format. Expected 4 fields.");
            }

            String proposalNumber = tokens.get(0);
            String showDate = tokens.get(1);
            String showTime = tokens.get(2);
            String showDuration = tokens.get(3);
            String showLocation = tokens.get(4);



        return new ShowProposalDTO(proposalNumber, showDate, showTime, showDuration, showLocation);
    }
}