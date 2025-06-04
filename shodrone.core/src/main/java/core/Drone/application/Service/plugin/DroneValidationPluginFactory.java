package core.Drone.application.Service.plugin;

public class DroneValidationPluginFactory {

    private static DroneValidationPlugin instance;

    public static DroneValidationPlugin getInstance() {
        if (instance == null) {
            throw new IllegalStateException("No DroneValidationPlugin implementation registered.");
        }
        return instance;
    }

    public static void registerInstance(DroneValidationPlugin customInstance) {
        if (instance != null) {
            throw new IllegalStateException("TemplateValidationPlugin implementation already registered.");
        }
        instance = customInstance;
    }
}