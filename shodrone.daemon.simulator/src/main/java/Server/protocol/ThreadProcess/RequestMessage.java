package Server.protocol.ThreadProcess;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * This class represents a request message that contains the raw input from the user
 * and a blocking queue for the response.
 * It is used to handle requests in a thread-safe manner, allowing the server
 * to wait for a response to be processed before continuing.
 *
 * @author Diogo Veiros
 */
public class RequestMessage {
    public final String rawInput;
    public final BlockingQueue<String> responseQueue;

    public RequestMessage(String rawInput) {
        this.rawInput = rawInput;
        this.responseQueue = new ArrayBlockingQueue<>(1);
    }
}
