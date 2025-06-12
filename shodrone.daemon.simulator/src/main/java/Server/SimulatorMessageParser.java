package Server;

import Server.protocol.*;
import core.Daemon.customerApp.Controller.UserAppServerController;
import core.Daemon.simulation.Controller.SimulatorServerController;
import eapli.framework.csv.util.CsvLineMarshaler;
import eapli.framework.infrastructure.authz.application.Authenticator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Parses messages from the customer app and builds the corresponding request objects.
 */
public class SimulatorMessageParser {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LogManager.getLogger(SimulatorMessageParser.class);

    /**
     * The controller that will handle the requests.
     */
    private final SimulatorServerController controller;

    /**
     * The authentication service used to verify user credentials.
     */
    private final Authenticator authenticationService;

    /**
     * Constructor for the CustomerAppMessageParser.
     *
     * @param controller            The controller that will handle the requests.
     * @param authenticationService The authentication service used to verify user credentials.
     */
    public SimulatorMessageParser(final SimulatorServerController controller,
                                  Authenticator authenticationService) {
        this.controller = controller;
        this.authenticationService = authenticationService;
    }

    /**
     * Get the controller.
     *
     * @return The controller.
     */
    private SimulatorServerController getController() {
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

            if ("EDIT_CONFIG".equals(command) ) {
                request = parseEditConfig(inputLine, tokens);
            }

            if ("GENERATE_SIMULATION_REPORT".equals( command)) {
                request = parseGenerateSimulationReport(inputLine, tokens);
            }

        } catch (final Exception e) {
            LOGGER.info("Unable to parse request: {}", inputLine);
            request = new BadRequest(inputLine, "Unable to parse request");
        }

        return request;
    }

    private UserAppRequest parseGenerateSimulationReport(String inputLine, String[] tokens) {
        if (tokens.length != 2) {
            return new BadRequest(inputLine, "Wrong number of parameters");
        } else {
            try {
                String path = CsvLineMarshaler.unquote(tokens[1]);
                return new GenerateSimulationReportRequest(getController(), inputLine, path);
            } catch (Exception e) {
                return new BadRequest(inputLine, "Invalid parameter format");
            }
        }
    }

    private UserAppRequest parseEditConfig(String inputLine, String[] tokens) {
        if (tokens.length != 10) {
            return new BadRequest(inputLine, "Wrong number of parameters");
        } else {
            try {
                String configFileName = CsvLineMarshaler.unquote(tokens[1]);
                String inputDirectory = CsvLineMarshaler.unquote(tokens[2]);
                int maxCollisions = Integer.parseInt(tokens[3]);
                int numDrones = Integer.parseInt(tokens[4]);
                int droneRadius = Integer.parseInt(tokens[5]);
                int xMax = Integer.parseInt(tokens[6]);
                int yMax = Integer.parseInt(tokens[7]);
                int zMax = Integer.parseInt(tokens[8]);
                int timeStep = Integer.parseInt(tokens[9]);

                return new EditConfigRequest(getController(), inputLine, configFileName, inputDirectory, maxCollisions,
                        numDrones, droneRadius, xMax, yMax, zMax, timeStep);
            } catch (NumberFormatException e) {
                return new BadRequest(inputLine, "Invalid number format in parameters");
            }
        }
    }

}