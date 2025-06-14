package core.ShowProposal.application;

import core.Persistence.PersistenceContext;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.CustomerFeedbackStatus;
import core.ShowProposal.domain.ValueObjects.ShowProposalStatus;
import core.ShowProposal.repositories.ShowProposalRepository;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class AccShowPropController {
    private final ShowProposalRepository showProposalRepository = PersistenceContext.repositories().proposals();

    public Iterable<ShowProposal> getShowProposalCheckedList(){
        return showProposalRepository.findAllCheckedProposals();
    }

    public boolean acceptProp(ShowProposal showProposal) {
        verifyShowProp(showProposal);
        showProposal.setShowProposalStatus(ShowProposalStatus.ACCEPTED);
        showProposalRepository.save(showProposal);
        return true;
    }

    public boolean verifyShowProp(ShowProposal showProposal) {
        if (showProposal == null) {
            return false;
        }
        if (showProposal.customerFeedback() == null) {
            return false;
        }
        return true;
    }


}
