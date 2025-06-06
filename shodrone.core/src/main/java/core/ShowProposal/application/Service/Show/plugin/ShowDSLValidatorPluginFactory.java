package core.ShowProposal.application.Service.Show.plugin;

public class ShowDSLValidatorPluginFactory {

    private static ShowDSLValidatorPlugin instance;

    public static ShowDSLValidatorPlugin getInstance() {
        if (instance == null) {
            throw new IllegalStateException("No ShowDSLValidatorPlugin implementation registered.");
        }
        return instance;
    }

    public static void registerInstance(ShowDSLValidatorPlugin customInstance) {
        if (instance != null) {
            throw new IllegalStateException("ShowDSLValidatorPlugin implementation already registered.");
        }
        instance = customInstance;
    }
}