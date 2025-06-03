package core.ShowProposal.application;

import core.Persistence.PersistenceContext;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.CustFeedbackStatus;
import core.ShowProposal.domain.ValueObjects.ShowProposalStatus;
import core.ShowProposal.repositories.ShowProposalRepository;

import javax.swing.plaf.PanelUI;

public class AccShowPropController {
    private final ShowProposalRepository showProposalRepository = PersistenceContext.repositories().proposals();
    public Iterable<ShowProposal> getShowProposalChekedList(){
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
        if (showProposal.feedbackStatus() == CustFeedbackStatus.PENDING) {
            return false;
        }
        return true;
    }


}
