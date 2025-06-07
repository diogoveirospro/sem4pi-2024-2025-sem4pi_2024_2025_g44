package Server;

import core.Persistence.Application;
import core.Persistence.PersistenceContext;
import core.Daemon.customerApp.Controller.UserAppServerController;
import core.User.domain.ShodronePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaemonUserBootstrapper {
    private static final Logger LOGGER = LogManager.getLogger(DaemonUserBootstrapper.class);
    private static final String SERVER_PORT = Application.settings().databasePort();


    public static void main(final String[] args) {
        LOGGER.info("Starting Shodrone customer user Server");
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new ShodronePasswordPolicy(),
                new PlainTextEncoder());
        LOGGER.info("Configuring the server on port {}", SERVER_PORT);
        final var server = new CustomerAppServer(buildServerDependencies());
        server.start(Integer.parseInt(SERVER_PORT), true);
        LOGGER.info("Exiting the server");
        System.exit(0);
    }

    private static CustomerAppMessageParser buildServerDependencies() {
        return new CustomerAppMessageParser( new UserAppServerController(),
                AuthzRegistry.authenticationService());
    }
}
