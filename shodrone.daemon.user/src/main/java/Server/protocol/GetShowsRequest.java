package Server.protocol;

import core.Daemon.customerApp.Controller.UserAppServerController;
import core.Daemon.reporting.shows.ShowReporting;
import core.Drone.domain.Entities.Drone;
import core.Figure.domain.Entities.Figure;
import core.ModelOfDrone.domain.Entities.Model;

import java.util.List;
import java.util.Map;

public class GetShowsRequest extends UserAppRequest {
    private final String vatNumber;

    public GetShowsRequest(final UserAppServerController controller, final String inputRequest, String vatNumber) {
        super(controller, inputRequest);
        this.vatNumber = vatNumber;
    }

    @Override
    public String execute() {
        try {
            if (vatNumber == null || vatNumber.isBlank()) {
                return buildBadRequest("Invalid VAT Number");
            }

            List<ShowReporting> shows = controller.getShowsByVatNumber(vatNumber);
            if (shows.isEmpty()) {
                return buildBadRequest("No shows found for the provided VAT Number");
            }

            return buildResponse(shows);
        } catch (Exception e) {
            return buildServerError(e.getMessage());
        }
    }

    private String buildResponse(List<ShowReporting> shows) {
        StringBuilder sb = new StringBuilder();

        sb.append("\"REQUEST_NUMBER\", \"PROPOSAL_NUMBER\", \"DRONE_CONFIGURATION\", \"VIDEO\", ")
                .append("\"FIGURES_CONFIGURATION\", \"SHOW_DESCRIPTION\", \"SHOW_LOCATION\", \"SHOW_DATE\", \"SHOW_TIME\", ")
                .append("\"QUANTITY_OF_DRONES\", \"INSURANCE\", \"SHOW_DURATION\"\n");

        for (ShowReporting show : shows) {
            sb.append("\"").append(show.requestNumber.showRequestID()).append("\", ")
                    .append("\"").append(show.proposalNumber.proposalNumber()).append("\", ")
                    .append("\"").append(convertDroneConfiguration(show.droneConfiguration)).append("\", ")
                    .append("\"").append(show.video.toString()).append("\", ")
                    .append("\"").append(convertFiguresConfiguration(show.figuresConfiguration)).append("\", ")
                    .append("\"").append(show.showDescription.getDescription()).append("\", ")
                    .append("\"").append(show.showLocation.toString()).append("\", ")
                    .append("\"").append(show.showDate.toString()).append("\", ")
                    .append("\"").append(show.showTime.toString()).append("\", ")
                    .append(show.quantityOfDrones.getQuantityOfDrones()).append(", ")
                    .append("\"").append(show.insurance.toString()).append("\", ")
                    .append("\"").append(show.showDuration.toMinutes()).append(" minutes").append("\"\n");
        }

        return sb.toString();
    }


    private String convertDroneConfiguration(Map<Model, List<Drone>> droneConfiguration) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Model, List<Drone>> entry : droneConfiguration.entrySet()) {
            sb.append(entry.getKey().identity().toString()).append(": ");
            List<Drone> drones = entry.getValue();
            for (int i = 0; i < drones.size(); i++) {
                sb.append(drones.get(i).getSerialNumber().value());
                if (i < drones.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("; ");
        }
        return sb.toString().trim();
    }

    private String convertFiguresConfiguration(List<Figure> figuresConfiguration) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Figure figure : figuresConfiguration) {
            sb.append(figure.name().toString());
            if (i < figuresConfiguration.size() - 1) {
                sb.append(", ");
            }
            i++;
        }
        return sb.toString();
    }
}