package core.ModelOfDrone.domain.Entities;

import core.ModelOfDrone.domain.ValueObjects.PositionTolerance;
import core.ModelOfDrone.domain.ValueObjects.SafetyStatus;
import core.ModelOfDrone.domain.ValueObjects.WindSpeed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    private Map<WindSpeed, PositionTolerance> configMap;
    private Configuration config;

    @BeforeEach
    void setUp() {
        configMap = new LinkedHashMap<>();
        configMap.put(new WindSpeed(0, 10), new PositionTolerance(0.5));
        configMap.put(new WindSpeed(10, 999), new PositionTolerance(-1.0));
        config = new Configuration(configMap, SafetyStatus.SAFE);
    }

    @Test
    void testValidConfigurationCreation() {
        assertNotNull(config);
        assertEquals(SafetyStatus.SAFE, config.getSafetyStatus());
        assertEquals(configMap, config.config());
    }

    @Test
    void testConfigurationCreationWithNullOrEmptyMapThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Configuration(null, SafetyStatus.SAFE));
        assertThrows(IllegalArgumentException.class, () -> new Configuration(new HashMap<>(), SafetyStatus.SAFE));
    }

    @Test
    void testGetToleranceForWindSpeed() {
        assertEquals(new PositionTolerance(0.5), config.getToleranceForWindSpeed(5));
        assertEquals(new PositionTolerance(-1.0), config.getToleranceForWindSpeed(999));
        assertNull(config.getToleranceForWindSpeed(1000));  // Outside any range
    }

    @Test
    void testSetSafetyStatusChangesToUnsafeIfWindSpeedAboveThreshold() {
        config.setSafetyStatus(11);
        assertEquals(SafetyStatus.UNSAFE, config.getSafetyStatus());
    }

    @Test
    void testSetSafetyStatusRemainsSafeIfWindSpeedBelowThreshold() {
        config.setSafetyStatus(5);
        assertEquals(SafetyStatus.SAFE, config.getSafetyStatus());
    }

    @Test
    void testSetConfigReplacesConfigMap() {
        Map<WindSpeed, PositionTolerance> newMap = new LinkedHashMap<>();
        newMap.put(new WindSpeed(0, 5), new PositionTolerance(1.0));
        config.setConfig(newMap);
        assertEquals(newMap, config.config());
    }

    @Test
    void testSetStatusManuallyChangesSafetyStatus() {
        config.setStatus(SafetyStatus.UNSAFE);
        assertEquals(SafetyStatus.UNSAFE, config.getSafetyStatus());

        config.setStatus(SafetyStatus.SAFE);
        assertEquals(SafetyStatus.SAFE, config.getSafetyStatus());
    }

    @Test
    void testToStringOutput() {
        String output = config.toString();
        assertTrue(output.contains("wind <= 10 -> 0.5m") || output.contains("0 < wind <= 10 -> 0.5m"));
        assertTrue(output.contains("10 < wind -> Not safe to fly"));
        assertTrue(output.contains("Safety Status: Safe when wind speed is less than 10m/s") ||
                output.contains("Safety Status: Safe when wind speed is less than 10 m/s"));
    }

    @Test
    void testEqualsAndHashCodeBasedOnConfigOnly() {
        Map<WindSpeed, PositionTolerance> map1 = new LinkedHashMap<>(configMap);
        Map<WindSpeed, PositionTolerance> map2 = new LinkedHashMap<>(configMap);

        Configuration conf1 = new Configuration(map1, SafetyStatus.SAFE);
        Configuration conf2 = new Configuration(map2, SafetyStatus.UNSAFE); // safetyStatus ignored in equals

        assertEquals(conf1, conf2);
        assertEquals(conf1.hashCode(), conf2.hashCode());

        // Different map => not equal
        map2.put(new WindSpeed(20, 30), new PositionTolerance(1.0));
        Configuration conf3 = new Configuration(map2, SafetyStatus.SAFE);
        assertNotEquals(conf1, conf3);
    }

    @Test
    void testEqualsWithDifferentObjects() {
        assertNotEquals(config, null);
        assertNotEquals(config, "String");
        assertEquals(config, config);
    }
}
