package Server.protocol;

import core.Daemon.customerApp.Controller.UserAppServerController;
import core.Daemon.reporting.proposals.DeliveryReporting;
import core.Daemon.reporting.shows.ShowReporting;
import core.Drone.domain.Entities.Drone;
import core.Figure.domain.Entities.Figure;
import core.ModelOfDrone.domain.Entities.Model;
import core.ShowProposal.domain.ValueObjects.ShowProposalDocument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private String buildResponse(DeliveryReporting proposal) { StringBuilder sb = new StringBuilder();
        sb.append("\"PROPOSAL_NUMBER\", \"DATE_OF_PROPOSAL\", \"TIME_OF_PROPOSAL\", ")
                .append("\"SHOW_DURATION\", \"SHOW_LOCATION\", \"File Bytes\"\n");
        sb.append("\"").append(proposal.proposalNumber.proposalNumber()).append("\", ")
                .append("\"").append(proposal.dateOfShow.toString()).append("\", ")
                .append("\"").append(proposal.timeOfShow.toString()).append("\", ")
                .append("\"").append(proposal.showDuration.toString()).append("\", ")
                .append("\"").append(proposal.showLocation.toString()).append("\", ")
                .append("\"").append(createFile(proposal.document)).append("\"\n");


        return sb.toString();
    }

    public String createFile(ShowProposalDocument document){
        try {
            if (document == null || document.file() == null || document.file().length == 0) {
                throw new IOException("Document file is empty or null");
            }
            Path filePath = Paths.get("shodrone.app.user/src/main/java/Shodrone/ShowProposalFiles/proposal.csv");
            Files.write(filePath, document.file());
            return filePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}