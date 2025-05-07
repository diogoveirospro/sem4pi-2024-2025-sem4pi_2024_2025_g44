package core.ModelOfDrone.domain.Entities;

import core.ModelOfDrone.domain.ValueObjects.*;
public class Model {
    private final ModelID modelId;
    private final WindTolerance windTolerance;
    private final WindSpeed windSpeed;
    private final PositionTolerance positionTolerance;
    private final SafetyStatus safetyStatus;
    public Model(ModelID modelId, WindTolerance windTolerance, WindSpeed windSpeed,
                 PositionTolerance positionTolerance, SafetyStatus safetyStatus) {
        this.modelId = modelId;
        this.windTolerance = windTolerance;
        this.windSpeed = windSpeed;
        this.positionTolerance = positionTolerance;
        this.safetyStatus = safetyStatus;
    }
}
