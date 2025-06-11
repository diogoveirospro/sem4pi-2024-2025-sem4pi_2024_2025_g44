package core.Drone.domain.Entities;

import core.Drone.domain.ValueObjects.*;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Drone implements AggregateRoot<Designation> {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private SerialNumber serialnumber;
    @Enumerated(EnumType.STRING)
    private DroneStatus droneStatus;

    @Embedded
    private UsageTime usageTime;

    @ManyToOne
    @JoinColumn(name = "Model", nullable = false)
    private Model model;

    @Embedded
    private RemovalReason removalReason;

    @Embedded
    private ProgramingLanguage programingLanguage;

    public Drone (SerialNumber serialnumber, Model model, RemovalReason removalReason, DroneStatus droneStatus, ProgramingLanguage programingLanguage) {
        this.serialnumber = serialnumber;
        this.droneStatus = DroneStatus.ACTIVE;
        this.model = model;
        this.removalReason = removalReason;
        this.usageTime = new UsageTime(LocalTime.of(0, 0));
        this.programingLanguage = programingLanguage;
    }

    protected Drone () {}
    public SerialNumber getSerialNumber() {
        return serialnumber;
    }

    public DroneStatus droneStatus() {
        return droneStatus;
    }

    public Model model() {
        return model;
    }

    public RemovalReason removalReason() {
        return removalReason;
    }

    // === Setters ===
    public void setSerialNumber(SerialNumber serialnumber) {
        this.serialnumber = serialnumber;
    }

    public void setDroneStatus(DroneStatus newStatus) {
        this.droneStatus = newStatus;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setRemovalReason(RemovalReason removalReason) {
        this.removalReason = removalReason;
    }

    public void addUsageTime(LocalTime usageTime) {
        this.usageTime.addTime(usageTime);
    }
    public ProgramingLanguage programingLanguage() {
        return programingLanguage;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public SerialNumber identity() {
        return serialnumber;
    }
}
