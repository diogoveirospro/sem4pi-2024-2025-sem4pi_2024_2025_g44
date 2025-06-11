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

    public ShowProposalDTO findProposalByDeliveryCode(String code) throws FailedRequestException {
        ShowProposalDTO showProposalDTO = server.getProposalByCode(code);
        if (showProposalDTO == null) {
            throw new FailedRequestException("No show proposal found for the code");
        }
        return showProposalDTO;
    }

    public String createFile(byte [] file){
        try {
            Path filePath = Paths.get("caminho/para/ficheiro.txt");
            Files.write(filePath, file);
            return filePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}