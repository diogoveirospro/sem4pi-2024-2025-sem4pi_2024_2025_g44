package Shodrone.console.AnalyseProposal.controller;

import Shodrone.DTO.CustomerDTO;
import Shodrone.DTO.ProposalDeliveryInfoDTO;
import Shodrone.DTO.ShodroneUserDTO;
import Shodrone.DTO.ShowProposalDTO;
import Shodrone.Server.CustomerAppProtocolProxy;
import Shodrone.exceptions.FailedRequestException;
import core.ProposalDeliveryInfo.domain.Entities.ProposalDeliveryInfo;
import core.ProposalDeliveryInfo.domain.ValueObjects.ProposalDeliveryInfoCode;
import core.ShowProposal.domain.Entities.ShowProposal;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.IOException;

/**
 * Controller for sending feedback on proposals.
 */
@UseCaseController
public class AnalyseProposalController {

    /**
     * Authorization service to check user permissions.
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Proxy to the server that handles customer app protocol requests.
     */
    private final CustomerAppProtocolProxy server = new CustomerAppProtocolProxy();

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

    public ProposalDeliveryInfoCode getProposalDeliveryInfoCode(ShowProposalDTO proposal) throws FailedRequestException, IOException {
        ProposalDeliveryInfoCode proposalDeliveryInfoCode = server.getProposalDeliveryInfoCode(proposal.proposalNumber);
        if (proposalDeliveryInfoCode == null) {
            throw new FailedRequestException("No delivery info found for the proposal");
        }
        return proposalDeliveryInfoCode;
    }

}