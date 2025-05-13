package shodrone.bootstrappers.SmokeTests.Backoffice;

import core.ModelOfDrone.application.CreateModelController;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.domain.ValueObjects.PositionTolerance;
import core.ModelOfDrone.domain.ValueObjects.WindSpeed;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ModelTeste implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ModelTeste.class);

    private final CreateModelController controller = new CreateModelController();

    @Override
    public boolean execute() {
        try {
            // Configuração para DroneX-200
            Map<PositionTolerance, WindSpeed> config = new HashMap<>();
            config.put(new PositionTolerance(0.0), new WindSpeed(0, 5));
            config.put(new PositionTolerance(0.3), new WindSpeed(5, 7));
            config.put(new PositionTolerance(0.5), new WindSpeed(7, 10));
            config.put(new PositionTolerance(0.8), new WindSpeed(10, 15));
            config.put(new PositionTolerance(-1), new WindSpeed(15, 999));

            register("DroneX-200", new Configuration(config));

            // Configuração para DroneY-300
            Map<PositionTolerance, WindSpeed> config1 = new HashMap<>();
            config1.put(new PositionTolerance(0.1), new WindSpeed(0, 3));
            config1.put(new PositionTolerance(0.25), new WindSpeed(3, 6));
            config1.put(new PositionTolerance(0.4), new WindSpeed(6, 9));
            config1.put(new PositionTolerance(0.6), new WindSpeed(9, 12));
            config1.put(new PositionTolerance(-1), new WindSpeed(12, 999));

            register("DroneY-300", new Configuration(config1));

            return true;
        } catch (Exception e) {
            LOGGER.error("Error during model bootstrap: {}", e.getMessage());
            return false;
        }
    }

    private void register(final String modelNameStr, final Configuration configuration) {
        try {
            ModelName modelName = new ModelName(modelNameStr);
            controller.createModel(modelName, configuration);
            LOGGER.info("Successfully registered drone model: {}", modelNameStr);
        } catch (Exception e) {
            LOGGER.error("Error registering drone model '{}': {}", modelNameStr, e.getMessage());
        }
    }
}
