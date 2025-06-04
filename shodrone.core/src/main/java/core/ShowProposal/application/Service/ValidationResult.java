package core.ShowProposal.application.Service;

import java.util.List;

public class ValidationResult {
    private final boolean isValid;

    public ValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
    }

    public boolean isValid() { return isValid; }
    public boolean isInvalid() { return !isValid; }
    public List<String> errors() {
        return List.of(); // Placeholder for actual error messages
    }
}
