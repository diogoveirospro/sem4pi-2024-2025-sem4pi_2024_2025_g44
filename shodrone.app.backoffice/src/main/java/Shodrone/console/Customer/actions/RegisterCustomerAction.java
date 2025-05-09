package Shodrone.console.Customer.actions;

import Shodrone.console.Customer.ui.RegisterCustomerUI;
import eapli.framework.actions.Action;

public class RegisterCustomerAction implements Action {
    @Override
    public boolean execute() {
        return new RegisterCustomerUI().show();
    }
}
