package core.Figure.application.Service;

public class DSLValidatorPluginFactory {

    private static DSLValidatorPlugin instance;

    public static DSLValidatorPlugin getInstance() {
        if (instance == null) {
            throw new IllegalStateException("No DSLValidatorPlugin implementation registered.");
        }
        return instance;
    }

    public static void registerInstance(DSLValidatorPlugin customInstance) {
        if (instance != null) {
            throw new IllegalStateException("DSLValidatorPlugin implementation already registered.");
        }
        instance = customInstance;
    }
}