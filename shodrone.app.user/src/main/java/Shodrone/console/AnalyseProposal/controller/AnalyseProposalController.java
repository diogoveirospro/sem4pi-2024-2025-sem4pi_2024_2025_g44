package Shodrone.console.AnalyseProposal.controller;

import Shodrone.DTO.CustomerDTO;
import Shodrone.DTO.ProposalDeliveryInfoDTO;
import Shodrone.DTO.ShodroneUserDTO;
import Shodrone.DTO.ShowProposalDTO;
import Shodrone.Server.CustomerAppProtocolProxy;
import Shodrone.exceptions.FailedRequestException;
import core.Category.repositories.CategoryRepository;
import core.Persistence.PersistenceContext;
import core.ProposalDeliveryInfo.domain.Entities.ProposalDeliveryInfo;
import core.ProposalDeliveryInfo.domain.ValueObjects.ProposalDeliveryInfoCode;
import core.ProposalDeliveryInfo.repositories.ProposalDeliveryInfoRepository;
import core.ShowProposal.domain.Entities.ShowProposal;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public ShowProposalDTO findProposalByDeliveryCode(String code) throws FailedRequestException, IOException {
        SystemUser currentUser = authz.session().get().authenticatedUser();

        ShodroneUserDTO shodroneUser = getShodroneUser(currentUser);
        CustomerDTO customer = server.getCustomerOfRepresentative(shodroneUser.email);

        ShowProposalDTO showProposalDTO = server.getProposalByCode(code);
        if (!validateFile(customer, showProposalDTO)) {
            throw new FailedRequestException("No show proposal found for the code: " + code + " or for the customer: " + customer.VatNumber);
        }
        return showProposalDTO;
    }

    public boolean validateFile(CustomerDTO customerdto, ShowProposalDTO showProposalDTO) throws FailedRequestException, IOException {
        if (showProposalDTO == null) {
            throw new FailedRequestException("No show proposal found for the code");
        }
        Iterable<ShowProposalDTO> customerShowPropDTOS = server.getProposalsDelivered(customerdto.VatNumber);
        if (customerShowPropDTOS == null) {
            throw new FailedRequestException("No shows found for the customer");
        }
        for (ShowProposalDTO proposal : customerShowPropDTOS) {
            if (proposal.proposalNumber.equals(showProposalDTO.proposalNumber)) {
                return true;
            }
        }
        return false;
    }
}