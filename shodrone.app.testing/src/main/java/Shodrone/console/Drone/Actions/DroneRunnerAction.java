package Shodrone.console.Drone.Actions;

import Shodrone.console.Drone.ui.DroneRunnerUI;
import eapli.framework.actions.Action;

public class DroneRunnerAction implements Action {
    @Override
    public boolean execute() {
        return new DroneRunnerUI().show();
    }
}
