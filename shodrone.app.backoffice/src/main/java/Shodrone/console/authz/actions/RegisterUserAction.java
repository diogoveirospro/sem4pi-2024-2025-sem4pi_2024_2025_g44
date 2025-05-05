package Shodrone.console.authz.actions;

import Shodrone.console.authz.ui.RegisterUserUI;
import eapli.framework.actions.Action;

public class RegisterUserAction implements Action {
    @Override
    public boolean execute() {
        return new RegisterUserUI().show();
    }
}
