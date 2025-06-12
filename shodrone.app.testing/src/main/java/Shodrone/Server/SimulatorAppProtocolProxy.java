package Shodrone.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Shodrone.exceptions.FailedRequestException;
import Shodrone.requests.*;
import core.Persistence.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Proxy for the Customer App Protocol, handling communication with the server.
 */
public class SimulatorAppProtocolProxy {

    /**
     * Logger for the CustomerAppProtocolProxy class.
     */
    private static final Logger LOGGER = LogManager.getLogger(SimulatorAppProtocolProxy.class);

    /**
     * SimulatorSocket class handles the connection to the server, sending and receiving messages.
     */
    private static class SimulatorSocket {
        private Socket sock;
        private PrintWriter output;
        private BufferedReader input;
        private final String address = Application.settings().serverIP();
        private final String port = Application.settings().serverPort();

        public void connect() throws IOException {
            InetAddress serverIP = InetAddress.getByName(address);
            int serverPort = Integer.parseInt(port);
            sock = new Socket(serverIP, serverPort);
            output = new PrintWriter(sock.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            LOGGER.debug("Connected to {}", serverIP);
        }

        public void send(String request) {
            output.println(request);
            LOGGER.debug("Sent message\n-----\n{}\n-----", request);
        }

        public List<String> receive() throws IOException {
            List<String> response = new ArrayList<>();
            String line;
            while ((line = input.readLine()) != null && !line.isEmpty()) {
                response.add(line);
            }
            LOGGER.debug("Received message:\n----\n{}\n----", response);
            return response;
        }

        public List<String> sendAndReceive(String request) throws IOException {
            send(request);
            return receive();
        }

        public void stop() throws IOException {
            input.close();
            output.close();
            sock.close();
        }
    }

    public void sendEditConfigRequest(String configFileName, String inputDirectory, int maxCollisions, int numDrones,
                                      int droneRadius, int xMax, int yMax, int zMax, int timeStep) throws IOException, FailedRequestException {
        final var socket = new SimulatorSocket();
        socket.connect();
        final String request = new EditConfigRequest(configFileName, inputDirectory, maxCollisions, numDrones, droneRadius, xMax, yMax, zMax, timeStep).toRequest();
        List <String> response = socket.sendAndReceive(request);
        socket.stop();
        final MarshallerUnmarshaller marshallerUnmarshaller = new MarshallerUnmarshaller();
        marshallerUnmarshaller.verifyIfConfigWasEdited(response);
    }

    public void generateSimulationReport(String path) throws IOException, FailedRequestException {
        final var socket = new SimulatorSocket();
        socket.connect();
        final String request = new GenerateSimutationReportRequest(path).toRequest();
        List <String> response = socket.sendAndReceive(request);
        socket.stop();
        final MarshallerUnmarshaller marshallerUnmarshaller = new MarshallerUnmarshaller();
        marshallerUnmarshaller.generateReportIfResponseIsCorrect(response);
    }



}