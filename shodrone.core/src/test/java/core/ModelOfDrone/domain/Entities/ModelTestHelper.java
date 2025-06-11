package core.ModelOfDrone.domain.Entities;

import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.domain.ValueObjects.PositionTolerance;
import core.ModelOfDrone.domain.ValueObjects.SafetyStatus;
import core.ModelOfDrone.domain.ValueObjects.WindSpeed;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModelTestHelper {
    public static Model createDummyModel() {
        ModelName name = new ModelName("TestModel-X");
        WindSpeed windSpeed = new WindSpeed(0, 20);
        PositionTolerance tolerance = new PositionTolerance(0.5);

        Map<WindSpeed, PositionTolerance> configMap = new LinkedHashMap<>();
        configMap.put(windSpeed, tolerance);

        Configuration config = new Configuration(configMap, SafetyStatus.SAFE);
        return new Model(name, config);
    }
}