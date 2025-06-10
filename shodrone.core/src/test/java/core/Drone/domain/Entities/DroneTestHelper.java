package core.Drone.domain.Entities;

import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.RemovalReason;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.Entities.Model;
import core.Shared.domain.ValueObjects.Date;

import java.util.LinkedHashMap;
import java.util.Map;

public class DroneTestHelper {
    public static Drone createDummyDrone() {
        SerialNumber sn = new SerialNumber(1);
        Model model = core.ModelOfDrone.domain.Entities.ModelTestHelper.createDummyModel();

        Map<java.util.Date, String> reasons = new LinkedHashMap<>();
        reasons.put(new java.util.Date(), "Motivo de teste");
        RemovalReason reason = new RemovalReason(reasons);

        DroneStatus status = DroneStatus.ACTIVE;

        return new Drone(sn, model, reason, status);
    }
}
