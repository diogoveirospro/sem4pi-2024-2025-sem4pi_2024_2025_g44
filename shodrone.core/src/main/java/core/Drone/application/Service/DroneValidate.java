package core.Drone.application.Service;

import core.Drone.application.Service.plugin.DroneValidationPlugin;
import core.Drone.application.Service.plugin.DroneValidationPluginFactory;

public class DroneValidate {

    private final DroneValidationPlugin plugin;

    public DroneValidate(DroneValidationPlugin plugin) {
        this.plugin = plugin;
    }

    public DroneValidate() {
        // Obtain the default plugin instance
        this.plugin = DroneValidationPluginFactory.getInstance();
    }

    public DroneValidationResult validateTemplate(String templateCode) {
        return plugin.validateTemplate(templateCode);
    }
}