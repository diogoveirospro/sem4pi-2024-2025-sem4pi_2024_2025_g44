package core.Maintenance.domain.Entities;

import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class MaintenanceType implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    protected MaintenanceType() {}

    public MaintenanceType(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Maintenance type name cannot be null or blank.");
        this.name = name;
    }

    public Long id() {
        return id;
    }

    @Override
    public Long identity() {
        return id;
    }

    public String name() {
        return name;
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank())
            throw new IllegalArgumentException("New name cannot be null or blank.");
        this.name = newName;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof MaintenanceType)) return false;
        final MaintenanceType that = (MaintenanceType) other;
        return this.identity() != null && this.identity().equals(that.identity());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaintenanceType)) return false;
        MaintenanceType that = (MaintenanceType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}