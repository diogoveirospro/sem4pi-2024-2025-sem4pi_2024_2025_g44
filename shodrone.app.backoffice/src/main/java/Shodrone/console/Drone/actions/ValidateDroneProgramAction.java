package Shodrone.console.Drone.actions;

import Shodrone.console.Drone.ui.ValidateDroneProgramUI;
import eapli.framework.actions.Action;

public class ValidateDroneProgramAction implements Action {
    @Override
    public boolean execute() {
        return new ValidateDroneProgramUI().show();
    }
}
