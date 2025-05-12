package core.Drone.domain.Entities;

import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.RemovalReason;
import core.Drone.domain.ValueObjects.SerialNumber;
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
    @Embedded
    private ModelName modelName;

    @Embedded
    private RemovalReason removalReason;
    public Drone (SerialNumber serialnumber, ModelName modelName, RemovalReason removalReason){
        this.serialnumber = serialnumber;
        this.droneStatus = DroneStatus.ACTIVE;
        this.modelName = modelName;
        this.removalReason = removalReason;

    }

    protected Drone () {}
    public SerialNumber serialnumber(){return serialnumber();}
    public DroneStatus droneStatus(){return droneStatus();}

    public RemovalReason removalReason() {
        return removalReason;
    }

    public ModelName modelName(){return modelName();}

    public void setStatus(DroneStatus newStatus) {
        this.droneStatus = newStatus;
    }

    public ModelName getModelName(){
        return modelName;
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
