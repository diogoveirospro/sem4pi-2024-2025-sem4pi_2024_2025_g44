package core.Figure.application.Service;

public class DSLValidate {

    private final DSLValidatorPlugin plugin;

    public DSLValidate(DSLValidatorPlugin plugin) {
        this.plugin = plugin;
    }

    public DSLValidate() {
        // Obtain the default plugin instance
        this.plugin = DSLValidatorPluginFactory.getInstance();
    }

    public DSLValidationResult validate(String dslCode) {
        return plugin.validateDSL(dslCode);
    }
}