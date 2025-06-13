package Server.protocol;

import core.Daemon.simulation.Controller.SimulatorServerController;
import shodrone.presentation.UtilsUI;

import java.util.List;

public class GenerateSimulationReportRequest extends UserAppRequest {

    private final String path;

    public GenerateSimulationReportRequest(final SimulatorServerController controller, final String inputRequest,
                                           final String path) {
        super(controller, inputRequest);
        this.path = path;
    }

    @Override
    public String execute() {
        try {
            UtilsUI.runScript(path);
            List<String> content = controller.generateSimulationReport(path);
            return buildResponse(content);
        } catch (Exception e) {
            return buildServerError("An error occurred while processing the request");
        }
    }

    private String buildResponse(List<String> content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content is null or empty");
        }

        StringBuilder response = new StringBuilder();
        response.append("SIMULATION_REPORT_RESPONSE\n");

        for (String line : content) {
            if (line == null || line.trim().isEmpty()) {
                response.append("[EMPTY_LINE]\n"); // Changes to handle empty lines
            } else {
                response.append(line).append("\n");
            }
        }

        return response.toString();
    }
}
