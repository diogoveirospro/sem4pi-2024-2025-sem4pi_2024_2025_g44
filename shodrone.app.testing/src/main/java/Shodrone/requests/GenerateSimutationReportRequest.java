package Shodrone.requests;

public class GenerateSimutationReportRequest {
    private final String path;

    public GenerateSimutationReportRequest(String path) {
        this.path = path;
    }

    public String toRequest() {
        return String.format("GENERATE_SIMULATION_REPORT %s", path);
    }

    public String path() {
        return path;
    }
}
