package core.ShowProposal.domain.Entities;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.RemovalReason;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.domain.ValueObjects.PositionTolerance;
import core.ModelOfDrone.domain.ValueObjects.SafetyStatus;
import core.ModelOfDrone.domain.ValueObjects.WindSpeed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShowConfigurationEntryTest {

    private Model model;
    private Model model2;
    private Drone drone;
    private Drone drone2;

    @BeforeEach
    void setUp() {
        model = setUpModel();
        drone = setUpDrone();

        model2 = setUpModel2();
        drone2 = setUpDrone2();
    }

    private Drone setUpDrone() {
        SerialNumber serialNumber = new SerialNumber(1234);
        Map<LocalDate, String> reasons = new HashMap<>();
        LocalDate now = LocalDate.now();
        String wewe = "Test";
        reasons.put(now, wewe);
        RemovalReason removalReason = new RemovalReason(reasons);
        return new Drone(serialNumber, model, removalReason);
    }

    private Model setUpModel() {
        ModelName modelName = new ModelName("TestModel");
        // Setup Configuration with dummy values
        WindSpeed ws = new WindSpeed(0, 10);
        PositionTolerance pt = new PositionTolerance(0.5);
        Configuration configuration = new Configuration(
                Map.of(ws, pt),
                SafetyStatus.SAFE
        );

        return new Model(modelName, configuration);
    }

    private Drone setUpDrone2() {
        SerialNumber serialNumber = new SerialNumber(3452);
        Map<LocalDate, String> reasons = new HashMap<>();
        LocalDate now = LocalDate.now();
        String wewe = "Test2";
        reasons.put(now, wewe);
        RemovalReason removalReason = new RemovalReason(reasons);
        return new Drone(serialNumber, model, removalReason);
    }

    private Model setUpModel2() {
        ModelName modelName = new ModelName("TestModel2");
        // Setup Configuration with dummy values
        WindSpeed ws = new WindSpeed(2, 20);
        PositionTolerance pt = new PositionTolerance(1);
        Configuration configuration = new Configuration(
                Map.of(ws, pt),
                SafetyStatus.SAFE
        );

        return new Model(modelName, configuration);
    }

    @Test
    void testConstructorThrowsOnNullModel() {
        assertThrows(IllegalArgumentException.class, () -> new ShowConfigurationEntry(null, drone));
    }

    @Test
    void testConstructorThrowsOnNullDrone() {
        assertThrows(IllegalArgumentException.class, () -> new ShowConfigurationEntry(model, null));
    }

    @Test
    void testConstructorAndGetters() {
        ShowConfigurationEntry entry = new ShowConfigurationEntry(model, drone);
        assertEquals(model, entry.model());
        assertEquals(drone, entry.drone());
    }

    @Test
    void testEqualsAndHashCode() {
        ShowConfigurationEntry entry1 = new ShowConfigurationEntry(model, drone);
        ShowConfigurationEntry entry2 = new ShowConfigurationEntry(model, drone);
        assertEquals(entry1, entry2);
        assertEquals(entry1.hashCode(), entry2.hashCode());
    }

    @Test
    void testNotEqualsDifferentModel() {
        ShowConfigurationEntry entry1 = new ShowConfigurationEntry(model, drone);
        ShowConfigurationEntry entry2 = new ShowConfigurationEntry(model2, drone);
        assertNotEquals(entry1, entry2);
    }

    @Test
    void testNotEqualsDifferentDrone() {
        ShowConfigurationEntry entry1 = new ShowConfigurationEntry(model, drone);
        ShowConfigurationEntry entry2 = new ShowConfigurationEntry(model, drone2);
        assertNotEquals(entry1, entry2);
    }

    @Test
    void testEqualsItself() {
        ShowConfigurationEntry entry = new ShowConfigurationEntry(model, drone);
        assertEquals(entry, entry);
    }

    @Test
    void testNotEqualsNullOrOtherClass() {
        ShowConfigurationEntry entry = new ShowConfigurationEntry(model, drone);
        assertNotEquals(entry, null);
        assertNotEquals(entry, "string");
    }

    @Test
    void testDroneIventory() {
        ShowConfigurationEntry entry = new ShowConfigurationEntry(model, drone);
        assertEquals(entry.drone().droneStatus(), DroneStatus.ACTIVE);
    }
}