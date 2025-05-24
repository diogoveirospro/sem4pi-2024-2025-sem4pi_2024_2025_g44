package core.Figure.application.Service;

import java.util.List;

public class DSLValidationResult {
    private final boolean isValid;
    private final List<String> errors;

    public DSLValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
        this.errors = errors;
    }

    public boolean isValid() { return isValid; }
    public List<String> errors() { return errors; }
}
