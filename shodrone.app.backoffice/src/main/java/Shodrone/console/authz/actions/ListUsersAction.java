package Shodrone.console.authz.actions;

import Shodrone.console.authz.ui.ListUsersUI;
import eapli.framework.actions.Action;

public class ListUsersAction implements Action {
    @Override
    public boolean execute() {
        return new ListUsersUI().show();
    }
}
