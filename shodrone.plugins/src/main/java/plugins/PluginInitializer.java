package plugins;

import core.Drone.application.Service.plugin.DroneValidationPluginFactory;
import core.Figure.application.Service.plugin.DSLValidatorPluginFactory;
import core.ShowProposal.application.Service.Show.plugin.ShowDSLValidatorPlugin;
import core.ShowProposal.application.Service.Show.plugin.ShowDSLValidatorPluginFactory;
import core.ShowProposal.application.Service.plugin.DocumentGenerationPluginFactory;
import plugin.java.ANTLRDocumentGenerationPlugin;
import plugin.java.ANTLRDSLValidatorPlugin;
import plugin.java.ANTLRDroneValidationPlugin;
import plugin.java.ANTLRShowDSLValidatorPlugin;

public class PluginInitializer {

    public static void initialize() {
        DSLValidatorPluginFactory.registerInstance(new ANTLRDSLValidatorPlugin());
        DroneValidationPluginFactory.registerInstance(new ANTLRDroneValidationPlugin());
        DocumentGenerationPluginFactory.registerInstance(new ANTLRDocumentGenerationPlugin());
        ShowDSLValidatorPluginFactory.registerInstance(new ANTLRShowDSLValidatorPlugin());
    }
}