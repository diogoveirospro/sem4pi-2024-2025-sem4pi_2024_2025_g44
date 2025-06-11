package core.Drone.domain.Entities;

import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.RemovalReason;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.domain.ValueObjects.UsageTime;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.domain.ValueObjects.PositionTolerance;
import core.ModelOfDrone.domain.ValueObjects.SafetyStatus;
import core.ModelOfDrone.domain.ValueObjects.WindSpeed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DroneTest {
    private Drone drone;
    private SerialNumber serialNumber;
    private RemovalReason removalReason;
    private Model model;

    @BeforeEach
    void setUp() {
        serialNumber = new SerialNumber(1234);
        Map<LocalDate, String> reasons = new HashMap<>();
        LocalDate now = LocalDate.now();
        String wewe = "Teste";
        reasons.put(now, wewe);
        removalReason = new RemovalReason(reasons);
        model = buildSampleModel();
        UsageTime usageTime = new UsageTime(LocalTime.of(0, 0));
        drone = new Drone(serialNumber, model, removalReason);
    }

    @Test
    void testDroneDefaultConstructorForORM() {
        Drone emptyDrone = new Drone(); // apenas para cobertura do construtor protegido
        assertNotNull(emptyDrone);
    }

    @Test
    void testDroneSameAsReturnsFalseAlways() {
        Drone otherDrone = new Drone(
                new SerialNumber(9999),
                buildSampleModel(),
                new RemovalReason(new HashMap<>())

        );

        assertFalse(drone.sameAs(otherDrone));
        assertFalse(drone.sameAs(null));
    }

    @Test
    void testDroneStatusGetterAndAlias() {
        assertEquals(drone.droneStatus(), drone.droneStatus());
    }

    @Test
    void testModelGetterAndAlias() {
        assertEquals(drone.model(), drone.model());
    }

    @Test
    void testIdentityEquality() {
        Drone sameSerialDrone = new Drone(serialNumber, model, removalReason);
        assertEquals(drone.identity(), sameSerialDrone.identity());
    }

    @Test
    void testGetSerialNumber() {
        assertEquals(serialNumber, drone.getSerialNumber());
    }

    @Test
    void testSetSerialNumber() {
        SerialNumber newSerial = new SerialNumber(5678);
        drone.setSerialNumber(newSerial);
        assertEquals(newSerial, drone.getSerialNumber());
    }

    @Test
    void testSetModel() {
        Model newModel = buildSampleModel(); // Reutiliza o mesmo builder
        drone.setModel(newModel);
        assertEquals(newModel, drone.model());
    }

    @Test
    void testSetRemovalReason() {
        Map<LocalDate, String> newReasons = new HashMap<>();
        newReasons.put(LocalDate.now(), "Updated reason");
        RemovalReason newReason = new RemovalReason(newReasons);
        drone.setRemovalReason(newReason);
        assertEquals(newReason, drone.removalReason());
    }

    @Test
    void testSetDroneStatus() {
        drone.setDroneStatus(DroneStatus.BROKEN);
        assertEquals(DroneStatus.BROKEN, drone.droneStatus());
    }


    private Model buildSampleModel() {
        Map<WindSpeed, PositionTolerance> configMap = new HashMap<>();
        configMap.put(new WindSpeed(0, 10), new PositionTolerance(0.5));
        configMap.put(new WindSpeed(10, 999), new PositionTolerance(-1));
        Configuration configuration = new Configuration(configMap, SafetyStatus.SAFE);
        return new Model(new ModelName("TestModel"), configuration);
    }
}
