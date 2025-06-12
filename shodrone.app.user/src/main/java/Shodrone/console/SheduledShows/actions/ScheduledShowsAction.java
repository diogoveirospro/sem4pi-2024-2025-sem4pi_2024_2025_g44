package Shodrone.console.SheduledShows.actions;

import Shodrone.console.SheduledShows.ui.ScheduledShowsUI;
import eapli.framework.actions.Action;

public class ScheduledShowsAction implements Action {
    @Override
    public boolean execute() {
        return new ScheduledShowsUI().show();
    }
}
