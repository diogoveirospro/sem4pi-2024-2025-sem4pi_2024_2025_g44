package shodrone.bootstrappers.Demo.Backoffice;

import core.ModelOfDrone.application.CreateModelController;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.domain.ValueObjects.PositionTolerance;
import core.ModelOfDrone.domain.ValueObjects.WindSpeed;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Array;
import java.util.HashMap;
import java.util.Map;

public class ModelBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ModelBootstrapper.class);

    private final CreateModelController controller = new CreateModelController();

    @Override
    public boolean execute() {
        WindSpeed windSpeed = new WindSpeed(0, 5);
        WindSpeed windSpeed1 = new WindSpeed(5, 7);
        WindSpeed windSpeed2 = new WindSpeed(7, 10);
        WindSpeed windSpeed3 = new WindSpeed(10, 15);
        WindSpeed windSpeed4 = new WindSpeed(15, 999);

        PositionTolerance positionTolerance = new PositionTolerance(0.0);
        PositionTolerance positionTolerance1 = new PositionTolerance(0.3);
        PositionTolerance positionTolerance2 = new PositionTolerance(0.5);
        PositionTolerance positionTolerance3 = new PositionTolerance(0.8);
        PositionTolerance positionTolerance4 = new PositionTolerance(-1);

        Map<PositionTolerance, WindSpeed> config = new HashMap<>();
        config.put(positionTolerance, windSpeed);
        config.put(positionTolerance1, windSpeed1);
        config.put(positionTolerance2, windSpeed2);
        config.put(positionTolerance3, windSpeed3);
        config.put(positionTolerance4, windSpeed4);

        register("DroneX-200", new Configuration( config));

        WindSpeed wsA = new WindSpeed(0, 3);
        WindSpeed wsB = new WindSpeed(3, 6);
        WindSpeed wsC = new WindSpeed(6, 9);
        WindSpeed wsD = new WindSpeed(9, 12);
        WindSpeed wsE = new WindSpeed(12, 20);

        PositionTolerance ptA = new PositionTolerance(0.1);
        PositionTolerance ptB = new PositionTolerance(0.25);
        PositionTolerance ptC = new PositionTolerance(0.4);
        PositionTolerance ptD = new PositionTolerance(0.6);
        PositionTolerance ptE = new PositionTolerance(-1); // talvez usado como tolerância "padrão" para extrapolação

        Map<PositionTolerance, WindSpeed> config1 = new HashMap<>();
        config1.put(ptA, wsA);
        config1.put(ptB, wsB);
        config1.put(ptC, wsC);
        config1.put(ptD, wsD);
        config1.put(ptE, wsE);

        register("DroneY-300", new Configuration(config1));

        return true;
    }

    private void register(final String modelNameStr, final Configuration config) {
        try {
            ModelName modelName = new ModelName(modelNameStr);
            Model model = new Model(modelName, config);
            controller.createModel(modelName, config);
            LOGGER.info("Successfully registered drone model: {}", modelNameStr);
        } catch (Exception e) {
            LOGGER.error("Error registering drone model '{}': {}", modelNameStr, e.getMessage());
        }
    }
}
