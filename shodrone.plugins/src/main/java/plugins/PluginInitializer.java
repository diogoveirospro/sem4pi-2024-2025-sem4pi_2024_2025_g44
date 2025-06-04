package plugins;

import core.Drone.application.Service.plugin.DroneValidationPluginFactory;
import core.Figure.application.Service.plugin.DSLValidatorPluginFactory;
import core.ShowProposal.application.Service.plugin.DocumentGenerationPluginFactory;
import plugin.java.ANTLRDocumentGenerationPlugin;
import plugin.java.ANTLRDSLValidatorPlugin;
import plugin.java.ANTLRDroneValidationPlugin;

public class PluginInitializer {

    public static void initialize() {
        DSLValidatorPluginFactory.registerInstance(new ANTLRDSLValidatorPlugin());
        DroneValidationPluginFactory.registerInstance(new ANTLRDroneValidationPlugin());
        DocumentGenerationPluginFactory.registerInstance(new ANTLRDocumentGenerationPlugin());
    }
}