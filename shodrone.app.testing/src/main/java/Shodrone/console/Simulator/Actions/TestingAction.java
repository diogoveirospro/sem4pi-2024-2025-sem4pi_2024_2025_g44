package Shodrone.console.Simulator.Actions;

import Shodrone.console.Simulator.ui.SimulateShowUI;
import eapli.framework.actions.Action;

public class TestingAction implements Action {
    @Override
    public boolean execute() {
        return new SimulateShowUI().show();
    }
}
