package core.Drone.application.Service.plugin;

import core.Drone.application.Service.DroneValidationResult;

public interface DroneValidationPlugin {
    DroneValidationResult validateTemplate(String code);
}
