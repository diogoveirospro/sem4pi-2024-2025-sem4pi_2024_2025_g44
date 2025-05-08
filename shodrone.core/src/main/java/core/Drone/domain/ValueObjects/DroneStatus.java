package core.Drone.domain.ValueObjects;

public enum DroneStatus {

    ACTIVE("Active"),
    MAINTENACE("Maintenace"),
    REMOVED("Removed"),
    BROKEN ("Broken");

    private final String status;

    DroneStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
