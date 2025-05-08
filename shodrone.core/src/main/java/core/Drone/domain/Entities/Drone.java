package core.Drone.domain.Entities;

import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.ValueObjects.ModelName;

public class Drone {
    private SerialNumber serialnumber;
    private DroneStatus droneStatus;
    private ModelName modelName;
    public Drone (SerialNumber serialnumber, DroneStatus  droneStatus, ModelName modelName){
        this.serialnumber = serialnumber;
        this.droneStatus = droneStatus;
        this.modelName = modelName;

    }

    public SerialNumber serialnumber(){return serialnumber();}
    public DroneStatus droneStatus(){return droneStatus();}

    public ModelName modelID(){return modelID();}

}
