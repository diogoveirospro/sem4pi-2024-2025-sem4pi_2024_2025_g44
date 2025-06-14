package Server;

import Server.protocol.*;
import core.Daemon.droneRunner.Controller.DroneRunnerController;
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
    private final DroneRunnerController controller;

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
    public DroneRunnerMessageParser(final DroneRunnerController controller,
                                    Authenticator authenticationService) {
        this.controller = controller;
        this.authenticationService = authenticationService;
    }

    /**
     * Get the controller.
     *
     * @return The controller.
     */
    private DroneRunnerController getController() {
        return controller;
    }

    /**
     * Parse and build the request.
     *
     * @param inputLine
     * @return
     */
    public DroneRunnerAppRequest parse(final String inputLine) {
        DroneRunnerAppRequest request = new UnknownRequest(inputLine);

        try {
            int firstSpaceIndex = inputLine.indexOf(' ');
            String command = (firstSpaceIndex == -1) ? inputLine : inputLine.substring(0, firstSpaceIndex);

            String[] tokens = inputLine.split(" ");

            if ("SEND_DRONE_RUNNER_FILE".equals(command)) {
                request = parseSendDroneRunnerFileRequest(inputLine,tokens);
            }

        } catch (final Exception e) {
            LOGGER.info("Unable to parse request: {}", inputLine);
            request = new BadRequest(inputLine, "Unable to parse request");
        }

        return request;
    }

    private DroneRunnerAppRequest parseSendDroneRunnerFileRequest(String inputLine, String[] tokens) {
        if (tokens.length <= 2) {
            return new BadRequest(tokens[0], "Wrong number of parameters");
        } else {
            String filePath = tokens[1];
            StringBuilder droneInfo = new StringBuilder();
            for (int i = 2; i < tokens.length; i++) {
                droneInfo.append(tokens[i]).append(" ");
            }
            if (droneInfo.length() > 0) {
                droneInfo.setLength(droneInfo.length() - 1);
            }
            return new SendDroneRunnerFileRequest(getController(), inputLine,filePath, droneInfo.toString());
        }
    }


}