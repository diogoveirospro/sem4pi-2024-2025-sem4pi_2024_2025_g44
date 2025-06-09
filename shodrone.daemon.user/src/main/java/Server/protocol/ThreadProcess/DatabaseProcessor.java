package Server.protocol.ThreadProcess;

import Server.CustomerAppMessageParser;
import Server.protocol.UserAppRequest;

import java.util.concurrent.BlockingQueue;

public class DatabaseProcessor extends Thread {
    private final BlockingQueue<RequestMessage> requestQueue;
    private final CustomerAppMessageParser parser;

    public DatabaseProcessor(BlockingQueue<RequestMessage> requestQueue, CustomerAppMessageParser parser) {
        this.requestQueue = requestQueue;
        this.parser = parser;
    }

    @Override
    public void run() {
        while (true) {
            try {
                RequestMessage request = requestQueue.take(); // waits for request
                UserAppRequest parsed = parser.parse(request.rawInput);
                String response = parsed.execute();
                request.responseQueue.put(response); // return result
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
