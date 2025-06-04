package plugins;

import core.Drone.application.Service.plugin.DroneValidationPluginFactory;
import core.Figure.application.Service.plugin.DSLValidatorPluginFactory;
import plugin.java.ANTLRDSLValidatorPlugin;
import plugin.java.ANTLRDroneValidationPlugin;

public class PluginInitializer {

    public static void initialize() {
        DSLValidatorPluginFactory.registerInstance(new ANTLRDSLValidatorPlugin());
        DroneValidationPluginFactory.registerInstance(new ANTLRDroneValidationPlugin());
    }
}