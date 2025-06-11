package core.Drone.domain.Entities;

import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.RemovalReason;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.Entities.Model;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class DroneTestHelper {
    public static Drone createDummyDrone() {
        SerialNumber sn = new SerialNumber(1);
        Model model = core.ModelOfDrone.domain.Entities.ModelTestHelper.createDummyModel();

        Map<LocalDate, String> reasons = new LinkedHashMap<>();
        reasons.put(LocalDate.now(), "Motivo de teste");
        RemovalReason reason = new RemovalReason(reasons);

        return new Drone(sn, model, reason);
    }
}
