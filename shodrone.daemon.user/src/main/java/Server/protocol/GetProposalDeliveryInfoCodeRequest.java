package Server.protocol;

import core.Daemon.customerApp.Controller.UserAppServerController;
import core.Daemon.reporting.proposals.DeliveryReporting;

import java.util.List;

public class GetProposalDeliveryInfoCodeRequest extends UserAppRequest{
    private final String vatNumber;

    public GetProposalDeliveryInfoCodeRequest(final UserAppServerController controller, final String inputRequest, String vatNumber) {
        super(controller, inputRequest);
        this.vatNumber = vatNumber;
    }

    @Override
    public String execute() {
        try {
            if (vatNumber == null || vatNumber.isBlank()) {
                return buildBadRequest("Invalid VAT Number");
            }

            List<DeliveryReporting> shows = controller.getDeliveryProposalsOfCustomer(vatNumber);
            if (shows.isEmpty()) {
                return buildBadRequest("No Proposals found for the provided VAT Number");
            }

            return buildResponse(shows);
        } catch (Exception e) {
            return buildServerError(e.getMessage());
        }
    }

    private String buildResponse(List<DeliveryReporting> proposals) {
        StringBuilder sb = new StringBuilder();

        sb.append("\"PROPOSAL_NUMBER\", \"DATE_OF_PROPOSAL\", \"TIME_OF_PROPOSAL\", ")
                .append("\"SHOW_DURATION\", \"SHOW_LOCATION\"\n");

        for (DeliveryReporting delivery : proposals) {
            sb.append("\"").append("\"").append(delivery.proposalNumber.proposalNumber()).append("\", ")
                    .append("\"").append(delivery.dateOfShow.toString()).append("\", ")
                    .append("\"").append(delivery.timeOfShow.toString()).append("\", ")
                    .append("\"").append(delivery.showDuration.toString()).append("\", ")
                    .append("\"").append(delivery.showLocation.toString()).append("\"\n");
        }

        return sb.toString();
    }
}
