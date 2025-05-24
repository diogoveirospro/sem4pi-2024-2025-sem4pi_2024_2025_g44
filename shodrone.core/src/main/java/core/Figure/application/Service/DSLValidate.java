package core.Figure.application.Service;


public class DSLValidate {

    private final DSLValidatorPlugin plugin;

    public DSLValidate(DSLValidatorPlugin plugin) {
        this.plugin = plugin;
    }

    public DSLValidate() {
        // TO DO
        plugin = null;
    }

    // Optional: if you want to validate from raw String
    public DSLValidationResult validate(String dslCode) {
        return plugin.validateDSL(dslCode);
    }
}

