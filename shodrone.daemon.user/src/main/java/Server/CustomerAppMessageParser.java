package Server;

import Server.protocol.*;
import core.Daemon.customerApp.Controller.UserAppServerController;
import eapli.framework.csv.util.CsvLineMarshaler;
import eapli.framework.infrastructure.authz.application.Authenticator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.util.Arrays;

public class CustomerAppMessageParser {
    private static final Logger LOGGER = LogManager.getLogger(CustomerAppMessageParser.class);

    private final UserAppServerController controller;
    private final Authenticator authenticationService;

    public CustomerAppMessageParser(final UserAppServerController controller,
                                           Authenticator authenticationService) {
        this.controller = controller;
        this.authenticationService = authenticationService;
    }

    private UserAppServerController getController() {
        return controller;
    }

    /**
     * Parse and build the request.
     *
     * @param inputLine
     * @return
     */
    public UserAppRequest parse(final String inputLine) {
        UserAppRequest request = new UnknownRequest(inputLine);

        try {
            int firstSpaceIndex = inputLine.indexOf(' ');
            String command = (firstSpaceIndex == -1) ? inputLine : inputLine.substring(0, firstSpaceIndex);

            String[] tokens = inputLine.split(" ");

            if ("GET_SHODRONE_USER".equals(command)) {
                request = parseGetShodroneUser(inputLine, tokens);
            }
            if ("GET_CUSTOMER".equals(command)) {
                request = parseGetCustomer(inputLine, tokens);
            }
            if ("GET_SHOWS".equals(command)) {
                request = parseGetShows(inputLine, tokens);
            }
        } catch (final Exception e) {
            LOGGER.info("Unable to parse request: {}", inputLine);
            request = new BadRequest(inputLine, "Unable to parse request");
        }

        return request;
    }


/*
    private BookingProtocolRequest parseBookAMeal(final String inputLine, final String[] tokens) {
        BookingProtocolRequest request;
        if (tokens.length != 4) {
            request = new BadRequest(inputLine, "Wrong number of parameters");
        } else if (isStringParam(tokens[1])) {
            request = new BadRequest(inputLine, "meal id must not be inside quotes");
        } else if (!isStringParam(tokens[2])) {
            request = new BadRequest(inputLine, "user id must be inside quotes");
        } else if (!isStringParam(tokens[3])) {
            request = new BadRequest(inputLine, "password must be inside quotes");
        } else {
            request = new BookAMealRequest(getController(), authenticationService, inputLine,
                    CsvLineMarshaler.unquote(tokens[1]), CsvLineMarshaler.unquote(tokens[2]),
                    CsvLineMarshaler.unquote(tokens[3]));
        }
        return request;
    }

    private boolean isStringParam(final String string) {
        return string.length() >= 2 && string.charAt(0) == '"' && string.charAt(string.length() - 1) == '"';
    }

    private BookingProtocolRequest parseGetAvailableMeals(final String inputLine, final String[] tokens) {
        BookingProtocolRequest request;
        if (tokens.length != 3) {
            request = new BadRequest(inputLine, "Wrong number of parameters");
        } else if (!isStringParam(tokens[1]) || !isStringParam(tokens[2])) {
            request = new BadRequest(inputLine, "Both date and meal type must be inside quotes");
        } else {
            request = new GetAvailableMealsRequest(getController(), inputLine, CsvLineMarshaler.unquote(tokens[1]),
                    CsvLineMarshaler.unquote(tokens[2]));
        }
        return request;
    }

 */

    private UserAppRequest parseGetShodroneUser(final String inputLine, final String[] tokens) {
        if (tokens.length != 2) {
            return new BadRequest(inputLine, "Wrong number of parameters");
        } else {
            return new GetShodroneUserRequest(getController(), inputLine, CsvLineMarshaler.unquote(tokens[1]));
        }
    }

    private UserAppRequest parseGetCustomer(final String inputLine, final String[] tokens) {
        if (tokens.length != 2) {
            return new BadRequest(inputLine, "Wrong number of parameters");
        } else {
            return new GetCustomerRequest(getController(), inputLine, CsvLineMarshaler.unquote(tokens[1]));
        }
    }

    private UserAppRequest parseGetShows(final String inputLine, final String[] tokens) {
        if (tokens.length != 2) {
            return new BadRequest(inputLine, "Wrong number of parameters");
        } else {
            return new GetShowsRequest(getController(), inputLine, CsvLineMarshaler.unquote(tokens[1]));
        }
    }
}
