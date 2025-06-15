package shodrone.bootstrappers.SmokeTests.Backoffice;

import core.ModelOfDrone.application.CreateModelController;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.ValueObjects.*;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ModelTeste implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ModelTeste.class);

    private final CreateModelController controller = new CreateModelController();

    @Override
    public boolean execute() {
        try {
            // Configuração para DroneX-200
            Map<WindSpeed, PositionTolerance> config = new HashMap<>();
            config.put(new WindSpeed(0, 5), new PositionTolerance(0.0));
            config.put(new WindSpeed(5, 7), new PositionTolerance(0.3));
            config.put(new WindSpeed(7, 10), new PositionTolerance(0.5));
            config.put(new WindSpeed(10, 15), new PositionTolerance(0.8));
            config.put(new WindSpeed(15, 999), new PositionTolerance(-1));

            register("DroneX-200", new Configuration(config, SafetyStatus.SAFE), new TimeLimit(Duration.ofHours(3)));

            // Configuração para DroneY-300
            Map<WindSpeed, PositionTolerance> config1 = new HashMap<>();
            config1.put(new WindSpeed(0, 3), new PositionTolerance(0.1));
            config1.put(new WindSpeed(3, 6), new PositionTolerance(0.25));
            config1.put(new WindSpeed(6, 9), new PositionTolerance(0.4));
            config1.put(new WindSpeed(9, 12), new PositionTolerance(0.6));
            config1.put(new WindSpeed(12, 999), new PositionTolerance(-1));

            register("DroneY-300", new Configuration(config1, SafetyStatus.SAFE), new TimeLimit(Duration.ofHours(3)));

            return true;
        } catch (Exception e) {
            LOGGER.error("Error during model bootstrap: {}", e.getMessage());
            return false;
        }
    }

    private void register(final String modelNameStr, final Configuration configuration, TimeLimit timeLimit) {
        try {
            ModelName modelName = new ModelName(modelNameStr);
            controller.createModel(modelName, configuration,timeLimit);
            LOGGER.info("Successfully registered drone model: {}", modelNameStr);
        } catch (Exception e) {
            LOGGER.error("Error registering drone model '{}': {}", modelNameStr, e.getMessage());
        }
    }
}
