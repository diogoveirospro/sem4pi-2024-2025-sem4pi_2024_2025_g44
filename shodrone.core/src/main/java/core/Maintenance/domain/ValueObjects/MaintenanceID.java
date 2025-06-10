package core.Maintenance.domain.ValueObjects;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class MaintenanceID implements Serializable, Comparable<MaintenanceID> {

    private String id;

    protected MaintenanceID() {}

    public MaintenanceID(String id) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null");
        this.id = id;
    }

    public static MaintenanceID newID() {
        return new MaintenanceID(UUID.randomUUID().toString());
    }

    public String id() {
        return id;
    }

    @Override
    public int compareTo(MaintenanceID other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaintenanceID)) return false;
        MaintenanceID that = (MaintenanceID) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }
}