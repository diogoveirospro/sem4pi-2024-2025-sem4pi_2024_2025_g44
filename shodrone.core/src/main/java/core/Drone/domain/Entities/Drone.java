package core.Drone.domain.Entities;

import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.RemovalReason;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.*;

@Entity
public class Drone implements AggregateRoot<Designation> {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private SerialNumber serialnumber;
    @Enumerated(EnumType.STRING)
    private DroneStatus droneStatus;

    @OneToOne
    private Model model;

    @Embedded
    private RemovalReason removalReason;
    public Drone (SerialNumber serialnumber, Model model, RemovalReason removalReason, DroneStatus droneStatus){
        this.serialnumber = serialnumber;
        this.droneStatus = DroneStatus.ACTIVE;
        this.model = model;
        this.removalReason = removalReason;

    }

    protected Drone () {}
    public SerialNumber serialnumber(){return serialnumber();}
    public DroneStatus droneStatus(){return droneStatus();}

    public RemovalReason removalReason() {
        return removalReason;
    }

    public Model model(){return model();}

    public void setStatus(DroneStatus newStatus) {
        this.droneStatus = newStatus;
    }

    public Model getModel(){
        return model;
    }

    public DroneStatus getDroneStatus(){
        return droneStatus;
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
