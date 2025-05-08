package core.Drone.domain.Entities;

import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelID;

public class Drone {
    private SerialNumber serialnumber;
    private DroneStatus droneStatus;
    private ModelID modelID;
    public Drone (SerialNumber serialnumber, DroneStatus  droneStatus, ModelID modelID){
        this.serialnumber = serialnumber;
        this.droneStatus = droneStatus;
        this.modelID = modelID;

    }

    public SerialNumber serialnumber(){return serialnumber();}
    public DroneStatus droneStatus(){return droneStatus();}

    public ModelID modelID(){return modelID();}

}
