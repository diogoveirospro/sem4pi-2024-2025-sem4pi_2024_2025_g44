package Server.protocol;

import core.Daemon.customerApp.Controller.UserAppServerController;
import core.Daemon.reporting.proposals.DeliveryReporting;
import core.Daemon.reporting.shows.ShowReporting;
import core.Drone.domain.Entities.Drone;
import core.Figure.domain.Entities.Figure;
import core.ModelOfDrone.domain.Entities.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GetPropByCodeRequest extends UserAppRequest {
    private final String code;

    public GetPropByCodeRequest(final UserAppServerController controller, final String inputRequest, String code) {
        super(controller, inputRequest);
        this.code = code;
    }

    @Override
    public String execute() {
        try {
            if (code == null || code.isBlank()) {
                return buildBadRequest("Invalid VAT Number");
            }

            DeliveryReporting show = controller.findProposalByDeliveryCode(code);
            if (show == null) {
                return buildBadRequest("No Proposals found for the provided VAT Number");
            }

            return buildResponse(show);
        } catch (Exception e) {
            return buildServerError(e.getMessage());
        }
    }

    private String buildResponse(DeliveryReporting proposal) {
        StringBuilder sb = new StringBuilder();

        sb.append("\"PROPOSAL_NUMBER\", \"DATE_OF_PROPOSAL\", \"TIME_OF_PROPOSAL\", ")
                .append("\"SHOW_DURATION\", \"SHOW_LOCATION\", \"File Bytes\"\n");
        sb.append("\"").append("\"").append(proposal.proposalNumber.proposalNumber()).append("\", ")
                .append("\"").append(proposal.dateOfShow.toString()).append("\", ")
                .append("\"").append(proposal.timeOfShow.toString()).append("\", ")
                .append("\"").append(proposal.showDuration.toString()).append("\", ")
                .append("\"").append(proposal.showLocation.toString()).append("\", ")
                .append("\"").append(Arrays.toString(proposal.file)).append("\"\n");


        return sb.toString();
    }
}