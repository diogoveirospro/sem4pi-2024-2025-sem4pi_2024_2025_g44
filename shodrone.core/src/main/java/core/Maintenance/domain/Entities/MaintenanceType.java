package core.Maintenance.domain.Entities;

import core.Shared.domain.ValueObjects.Name;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class MaintenanceType implements Serializable, AggregateRoot<Designation> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type_name", unique = true, nullable = false)
    private Name name;

    protected MaintenanceType() {}

    public MaintenanceType(Name name) {
        if (name == null || name.toString().isBlank())
            throw new IllegalArgumentException("Maintenance type name cannot be null or blank.");
        this.name = name;
    }

    public Long id() {
        return id;
    }

    @Override
    public Name identity() {
        return name;
    }

    public Name name() {
        return name;
    }

    public void rename(Name newName) {
        if (newName == null || newName.toString().isBlank())
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
        return this.name.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}