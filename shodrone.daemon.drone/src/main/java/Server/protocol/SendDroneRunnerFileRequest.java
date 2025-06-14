package Server.protocol;

import core.Daemon.droneRunner.Controller.DroneRunnerController;

public class SendDroneRunnerFileRequest extends DroneRunnerAppRequest{

    public String filePath;
    public String fileContent;

    public SendDroneRunnerFileRequest(DroneRunnerController controller, String inputLine, String filePath, String fileContent) {
        super(controller, inputLine);
        this.filePath = filePath;
        this.fileContent = fileContent;
    }

    @Override
    public String execute() {
        try {
            boolean success = controller.sendDroneRunnerFile(filePath, fileContent);
            return buildResponse(success);
        } catch ( Exception e) {
            return buildServerError("An error occurred while processing the request: " + e.getMessage());
        }

    }

    private String buildResponse(boolean success) {
        StringBuilder response = new StringBuilder();
        response.append("SEND_DRONE_RUNNER_FILE_RESPONSE\n");
        if (success) {
            response.append("File successfully sent.\n");
        } else {
            response.append("Failed to send file.\n");
        }
        return response.toString();
    }
}
