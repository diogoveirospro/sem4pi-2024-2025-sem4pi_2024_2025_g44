package Server;

import Server.protocol.ThreadProcess.DatabaseProcessor;
import Server.protocol.ThreadProcess.RequestMessage;
import core.Daemon.droneRunner.Controller.DroneRunnerController;
import core.Daemon.simulation.Controller.SimulatorServerController;
import core.Persistence.Application;
import core.Persistence.PersistenceContext;
import core.User.domain.ShodronePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DaemonDroneRunnerBootstrapper {
    private static final Logger LOGGER = LogManager.getLogger(DaemonDroneRunnerBootstrapper.class);
    private static final String SERVER_PORT = Application.settings().serverPort();
    private static final String QUEUE_CAPACITY = Application.settings().queueCapacity();

    public static void main(final String[] args) {
        LOGGER.info("Starting Shodrone simulator Server");

        AuthzRegistry.configure(
                PersistenceContext.repositories().users(),
                new ShodronePasswordPolicy(),
                new PlainTextEncoder()
        );

        int queueCapacity = Integer.parseInt(QUEUE_CAPACITY);
        final BlockingQueue<RequestMessage> requestQueue = new ArrayBlockingQueue<>(queueCapacity);

        final DroneRunnerMessageParser parser = buildServerDependencies();

        // Father
        LOGGER.info("Starting Database Processor (parent)");
        System.out.println("Starting Database Processor (parent)");
        new DatabaseProcessor(requestQueue, parser).start();

        // Son
        System.out.println("Starting Simulator Server (child) on port " + SERVER_PORT);
        LOGGER.info("Starting Simulator Server (child) on port {}", SERVER_PORT);
        final var server = new DroneRunnerServer(requestQueue);
        server.start(Integer.parseInt(SERVER_PORT), false);

        try {
            System.out.println("Shodrone simulator Server started successfully.");
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            LOGGER.error("Main thread interrupted", e);
        }


        System.exit(0);
    }

    private static DroneRunnerMessageParser buildServerDependencies() {
        return new DroneRunnerMessageParser(
                new DroneRunnerController(),
                AuthzRegistry.authenticationService()
        );
    }
}
