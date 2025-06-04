package core.Drone.application.Service;

import java.util.List;

public class DroneValidationResult {
    private final boolean isValid;
    private final List<String> errors;

    public DroneValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
        this.errors = errors;
    }

    public boolean isValid() { return isValid; }
    public List<String> errors() { return errors; }
}
