package core.ShowProposal.application.Service.plugin;

public class DocumentGenerationPluginFactory {
    private static DocumentGeneratorPlugin instance;

    public static DocumentGeneratorPlugin getInstance() {
        if (instance == null) {
            throw new IllegalStateException("No TemplateValidationPlugin implementation registered.");
        }
        return instance;
    }

    public static void registerInstance(DocumentGeneratorPlugin customInstance) {
        if (instance != null) {
            throw new IllegalStateException("TemplateValidationPlugin implementation already registered.");
        }
        instance = customInstance;
    }
}
