package core.Maintenance.domain.Entities;

import core.Drone.domain.Entities.Drone;
import core.Maintenance.domain.ValueObjects.MaintenanceID;
import core.Shared.domain.ValueObjects.Date;
import core.Shared.domain.ValueObjects.Description;
import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;



@Entity
public class Maintenance implements AggregateRoot<MaintenanceID> {

    @EmbeddedId
    private MaintenanceID id;

    @ManyToOne(optional = false)
    private Drone drone;

    @ManyToOne(optional = false)
    private MaintenanceType type;

    @Embedded
    private Description description;

    @Embedded
    private Date date;

    @Override
    public MaintenanceID identity() {
        return this.id;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Maintenance)) return false;
        final Maintenance that = (Maintenance) other;
        return this.identity().equals(that.identity());
    }

    protected Maintenance() {

    }

    public Maintenance(MaintenanceID id, Drone drone, MaintenanceType type, Description description, Date date) {
        if (drone == null || type == null || description == null || date == null)
            throw new IllegalArgumentException("All fields must be non-null.");

        this.id = id;
        this.drone = drone;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public MaintenanceID id() { return id; }
    public Drone drone() { return drone; }
    public MaintenanceType type() { return type; }
    public Description description() { return description; }
    public Date date() { return date; }
}
