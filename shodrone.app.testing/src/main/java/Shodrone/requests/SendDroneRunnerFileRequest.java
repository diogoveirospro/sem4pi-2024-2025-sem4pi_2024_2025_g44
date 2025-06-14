package Shodrone.requests;

public class SendDroneRunnerFileRequest {
    private final String filePath;
    private final String formattedContent;

    public SendDroneRunnerFileRequest(String filePath, String formattedContent) {
        this.filePath = filePath;
        this.formattedContent = formattedContent;
    }

    public String toRequest() {
        return String.format("SEND_DRONE_RUNNER_FILE %s %s", filePath, formattedContent);
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFormattedContent() {
        return formattedContent;
    }
}
