package Server;

import Server.protocol.*;
import core.Daemon.simulation.Controller.SimulatorServerController;
import eapli.framework.csv.util.CsvLineMarshaler;
import eapli.framework.infrastructure.authz.application.Authenticator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Parses messages from the customer app and builds the corresponding request objects.
 */
public class DroneRunnerMessageParser {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LogManager.getLogger(DroneRunnerMessageParser.class);

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
    public DroneRunnerMessageParser(final SimulatorServerController controller,
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


        } catch (final Exception e) {
            LOGGER.info("Unable to parse request: {}", inputLine);
            request = new BadRequest(inputLine, "Unable to parse request");
        }

        return request;
    }


}