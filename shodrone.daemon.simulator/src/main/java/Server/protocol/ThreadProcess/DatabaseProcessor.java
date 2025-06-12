package Server.protocol.ThreadProcess;

import Server.SimulatorMessageParser;
import Server.protocol.UserAppRequest;

import java.util.concurrent.BlockingQueue;

public class DatabaseProcessor extends Thread {
    private final BlockingQueue<RequestMessage> requestQueue;
    private final SimulatorMessageParser parser;

    public DatabaseProcessor(BlockingQueue<RequestMessage> requestQueue, SimulatorMessageParser parser) {
        this.requestQueue = requestQueue;
        this.parser = parser;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for request...");
                RequestMessage request = requestQueue.take(); // waits for request
                System.out.println("Processing request: " + request.rawInput);
                UserAppRequest parsed = parser.parse(request.rawInput);
                System.out.println("Parsed request: " + parsed);
                String response = parsed.execute();
                System.out.println("Response generated: " + response);
                request.responseQueue.put(response); // return result
            } catch (Exception e) {
                System.err.println("Error processing request: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
