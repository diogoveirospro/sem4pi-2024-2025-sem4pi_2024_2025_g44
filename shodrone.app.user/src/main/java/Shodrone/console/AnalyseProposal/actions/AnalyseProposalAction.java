package Shodrone.console.AnalyseProposal.actions;

import Shodrone.console.AnalyseProposal.ui.AnalyseProposalUI;
import Shodrone.console.SendFeedbackProposal.ui.SendFeedbackProposalUI;
import eapli.framework.actions.Action;

public class AnalyseProposalAction implements Action {
    @Override
    public boolean execute() {
        return new AnalyseProposalUI().show();
    }
}
