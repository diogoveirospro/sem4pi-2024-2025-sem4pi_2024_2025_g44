package plugins;

import core.Figure.application.Service.plugin.DSLValidatorPluginFactory;
import plugin.java.ANTLRDSLValidatorPlugin;

public class PluginInitializer {

    public static void initialize() {
        DSLValidatorPluginFactory.registerInstance(new ANTLRDSLValidatorPlugin());
    }
}