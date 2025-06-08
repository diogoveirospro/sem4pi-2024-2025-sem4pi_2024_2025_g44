package Shodrone.console.SendFeedbackProposal.controller;

import Shodrone.DTO.CustomerDTO;
import Shodrone.DTO.ShodroneUserDTO;
import Shodrone.DTO.ShowProposalDTO;
import Shodrone.Server.CustomerAppProtocolProxy;
import Shodrone.exceptions.FailedRequestException;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.IOException;
import java.util.List;

/**
 * Controller for sending feedback on proposals.
 */
@UseCaseController
public class SendFeedbackProposalController {

    /**
     * Authorization service to check user permissions.
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Proxy to the server that handles customer app protocol requests.
     */
    private final CustomerAppProtocolProxy server = new CustomerAppProtocolProxy();

    /**
     * Sends feedback on a proposal.
     *
     * @param proposalNumber the proposal number to send feedback for
     * @param decision       the decision made on the proposal (e.g., ACCEPTED, REJECTED)
     * @param feedback       additional feedback provided by the user
     * @return true if the feedback was successfully sent, false otherwise
     * @throws FailedRequestException if the request fails
     * @throws IOException            if an I/O error occurs
     */
    public boolean sendFeedbackProposal(String proposalNumber, String decision, String feedback)
            throws FailedRequestException, IOException {

        boolean result = server.sendFeedbackProposal(proposalNumber, decision, feedback);
        if (!result) {
            throw new FailedRequestException("Failed to send feedback for proposal: " + proposalNumber);
        }
        return result;
    }

    /**
     * Lists all proposals for the authenticated user.
     *
     * @return an iterable of ShowProposalDTO containing the proposals
     * @throws FailedRequestException if the request fails
     * @throws IOException            if an I/O error occurs
     */
    public Iterable<ShowProposalDTO> listProposals() throws FailedRequestException, IOException {
        SystemUser currentUser = authz.session().get().authenticatedUser();

        ShodroneUserDTO shodroneUser = getShodroneUser(currentUser);
        CustomerDTO customer = server.getCustomerOfRepresentative(shodroneUser.email);

        Iterable<ShowProposalDTO> proposals = server.getProposalsDelivered(customer.VatNumber);
        if (proposals == null) {
            throw new FailedRequestException("No shows found for the customer");
        }
        return proposals;
    }

    /**
     * Retrieves the Shodrone user associated with the current system user.
     *
     * @param currentUser the current system user
     * @return the ShodroneUserDTO for the current user
     * @throws FailedRequestException if the request fails
     * @throws IOException            if an I/O error occurs
     */
    private ShodroneUserDTO getShodroneUser(SystemUser currentUser)
            throws FailedRequestException, IOException {
        ShodroneUserDTO shodroneUser = server.getShodroneUser(currentUser.username().toString());
        if (shodroneUser == null) {
            throw new FailedRequestException("User not found");
        }
        return shodroneUser;
    }

}
