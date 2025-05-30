package core.Category.application.Service.plugin;

public class TemplateValidationPluginFactory {

    private static TemplateValidationPlugin instance;

    public static TemplateValidationPlugin getInstance() {
        if (instance == null) {
            throw new IllegalStateException("No TemplateValidationPlugin implementation registered.");
        }
        return instance;
    }

    public static void registerInstance(TemplateValidationPlugin customInstance) {
        if (instance != null) {
            throw new IllegalStateException("TemplateValidationPlugin implementation already registered.");
        }
        instance = customInstance;
    }
}