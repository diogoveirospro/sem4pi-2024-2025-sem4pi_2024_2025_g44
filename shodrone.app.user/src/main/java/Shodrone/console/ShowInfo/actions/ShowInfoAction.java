package Shodrone.console.ShowInfo.actions;

import Shodrone.console.ShowInfo.ui.ShowInfoUI;
import eapli.framework.actions.Action;

public class ShowInfoAction implements Action {
    @Override
    public boolean execute() {
        return new ShowInfoUI().show();
    }
}
