package core.ProposalDeliveryInfo.application;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.Persistence.PersistenceContext;
import core.ProposalDeliveryInfo.domain.Entities.ProposalDeliveryInfo;
import core.ProposalDeliveryInfo.domain.ValueObjects.ProposalDeliveryInfoCode;
import core.ProposalDeliveryInfo.repositories.ProposalDeliveryInfoRepository;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.ShowProposalStatus;
import core.ShowProposal.repositories.ShowProposalRepository;
import eapli.framework.application.UseCaseController;

/**
 * Controller for sending proposals.
 * This controller handles the logic for sending proposals to collaborators.
 */
@UseCaseController
public class SendProposalController {

    /**
     * Repository for managing proposal delivery information.
     */
    ProposalDeliveryInfoRepository deliveryRepo = PersistenceContext.repositories().deliveries();

    /**
     * Repository for managing show proposals.
     */
    ShowProposalRepository proposalsRepo = PersistenceContext.repositories().proposals();

    /**
     * Lists all proposals that are ready to be sent.
     * @return an iterable collection of ShowProposal objects that are ready to be sent.
     */
    public Iterable<ShowProposal> listReadyToSendProposals(){
        return proposalsRepo.findProposalsReadyToSend();
    }

    /**
     * Sends a proposal to a collaborator.
     * This method creates a delivery record and updates the proposal status to WAITING_FOR_RESPONSE.
     *
     * @param proposal the proposal to be sent
     * @param collaborator the collaborator to whom the proposal is sent
     * @return the unique code for the proposal delivery
     */
    public ProposalDeliveryInfoCode sendProposal(ShowProposal proposal, CRMCollaborator collaborator) {
        if (proposal == null) {
            throw new IllegalArgumentException("Proposal cannot be null");
        }

        ProposalDeliveryInfo delivery = ProposalDeliveryInfo.sendDelivery(proposal, collaborator);

        // Save the delivery info to the repository
        ProposalDeliveryInfo saveDelivery = deliveryRepo.save(delivery);

        // Update the proposal status to WAITING_FOR_RESPONSE
        proposal.setShowProposalStatus(ShowProposalStatus.WAITING_FOR_RESPONSE);
        proposalsRepo.save(proposal);

        return saveDelivery.identity();
    }
}
