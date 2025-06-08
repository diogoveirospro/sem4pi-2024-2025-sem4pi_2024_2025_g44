package Shodrone.console.SendFeedbackProposal.actions;

import Shodrone.console.SendFeedbackProposal.ui.SendFeedbackProposalUI;
import eapli.framework.actions.Action;

public class SendFeedbackProposalAction implements Action {
    @Override
    public boolean execute() {
        return new SendFeedbackProposalUI().show();
    }
}
