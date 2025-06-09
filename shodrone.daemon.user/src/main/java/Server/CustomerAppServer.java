package Server;

import Server.protocol.ThreadProcess.RequestMessage;
import Server.protocol.UserAppRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class CustomerAppServer {
    private static final Logger LOGGER = LogManager.getLogger(CustomerAppServer.class);

    private final BlockingQueue<RequestMessage> requestQueue;

    public CustomerAppServer(final BlockingQueue<RequestMessage> requestQueue) {
        this.requestQueue = requestQueue;
    }

    /**
     * Client socket.
     *
     * @author Paulo Gandra Sousa 01/06/2020
     */
    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private final BlockingQueue<RequestMessage> requestQueue;

        public ClientHandler(final Socket socket, final BlockingQueue<RequestMessage> requestQueue) {
            this.clientSocket = socket;
            this.requestQueue = requestQueue;
        }

        @Override
        public void run() {
            final var clientIP = clientSocket.getInetAddress();
            LOGGER.debug("Accepted connection from {}:{}", clientIP.getHostAddress(), clientSocket.getPort());

            try (var out = new PrintWriter(clientSocket.getOutputStream(), true);
                 var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    LOGGER.debug("Received message:----\n{}\n----", inputLine);
                    System.out.println("Received message:----\n" + inputLine + "\n----");

                    // Send to processor
                    RequestMessage message = new RequestMessage(inputLine);
                    requestQueue.put(message); // Send to DB port
                    String response = message.responseQueue.take(); // Wait for DB to respond

                    out.println(response);
                    LOGGER.debug("Sent message:----\n{}\n----", response);

                    // Note: No more request.isGoodbye() here since we don't parse in this thread
                    if (inputLine.trim().equalsIgnoreCase("GOODBYE")) {
                        break;
                    }
                }
            } catch (final Exception e) {
                LOGGER.error(e);
            } finally {
                try {
                    clientSocket.close();
                    LOGGER.debug("Closing client socket {}:{}", clientIP.getHostAddress(), clientSocket.getPort());
                } catch (final IOException e) {
                    LOGGER.error("While closing the client socket {}:{}", clientIP.getHostAddress(),
                            clientSocket.getPort(), e);
                }
                clientSocket = null;
            }
        }
    }


    /**
     * Wait for connections.
     * <p>
     * Suppress warning java:S2189 - Loops should not be infinite
     *
     * @param port
     */
    @SuppressWarnings("java:S2189")
    private void listen(final int port) {
        try (var serverSocket = new ServerSocket(port)) {
            while (true) {
                final var clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket, requestQueue).start();
            }
        } catch (final IOException e) {
            LOGGER.error(e);
        }
    }


    /**
     * Wait for connections.
     *
     * @param port
     * @param blocking
     *            if {@code false} the socket runs in its own thread and does not block calling
     *            thread.
     */
    public void start(final int port, final boolean blocking) {
        if (blocking) {
            listen(port);
        } else {
            new Thread(() -> listen(port)).start();
        }
    }
}
