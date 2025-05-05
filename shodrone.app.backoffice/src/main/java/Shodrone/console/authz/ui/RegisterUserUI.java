package Shodrone.console.authz.ui;

import eapli.framework.presentation.console.AbstractUI;

public class RegisterUserUI extends AbstractUI {
    @Override
    protected boolean doShow() {
        return false;
    }

    @Override
    public String headline() {
        return "Register User";
    }
}
