package core.ShowProposal.application.Service;

import java.util.List;

public class ValidationResult {
    private final boolean isValid;
    private final List<String> errors;

    public ValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
        this.errors = errors;
    }

    public boolean isValid() { return isValid; }
    public List<String> errors() { return errors; }
}
