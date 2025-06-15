package shodrone.bootstrappers.Demo.Backoffice;

import core.ModelOfDrone.application.CreateModelController;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.ValueObjects.*;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.presentation.UtilsUI;

import java.sql.Array;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ModelBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ModelBootstrapper.class);

    private final CreateModelController controller = new CreateModelController();

    @Override
    public boolean execute() {

        Map<WindSpeed, PositionTolerance> config = new HashMap<>();
        config.put(new WindSpeed(0, 5), new PositionTolerance(0.0));
        config.put(new WindSpeed(5, 7), new PositionTolerance(0.3));
        config.put(new WindSpeed(7, 10), new PositionTolerance(0.5));
        config.put(new WindSpeed(10, 15), new PositionTolerance(0.8));
        config.put(new WindSpeed(15, 999), new PositionTolerance(-1));

        register("droneTypeX", new Configuration(config, SafetyStatus.SAFE), new TimeLimit(Duration.ofHours(100)));

        Map<WindSpeed, PositionTolerance> config1 = new HashMap<>();
        config1.put(new WindSpeed(0, 3), new PositionTolerance(0.0));
        config1.put(new WindSpeed(3, 6), new PositionTolerance(0.25));
        config1.put(new WindSpeed(6, 9), new PositionTolerance(0.4));
        config1.put(new WindSpeed(9, 12), new PositionTolerance(0.6));
        config1.put(new WindSpeed(12, 999), new PositionTolerance(-1));

        register("droneTypeY", new Configuration(config1, SafetyStatus.SAFE), new TimeLimit(Duration.ofHours(35)));

        Map<WindSpeed, PositionTolerance> config2 = new HashMap<>();
        config2.put(new WindSpeed(0, 4), new PositionTolerance(0.0));
        config2.put(new WindSpeed(4, 8), new PositionTolerance(0.2));
        config2.put(new WindSpeed(8, 11), new PositionTolerance(0.6));
        config2.put(new WindSpeed(11, 999), new PositionTolerance(-1));

        register("droneTypeZ", new Configuration(config2, SafetyStatus.SAFE), new TimeLimit(Duration.ofHours(24)));

        Map<WindSpeed, PositionTolerance> config3 = new HashMap<>();
        config3.put(new WindSpeed(0, 6), new PositionTolerance(0.1));
        config3.put(new WindSpeed(6, 12), new PositionTolerance(0.4));
        config3.put(new WindSpeed(12, 16), new PositionTolerance(0.7));
        config3.put(new WindSpeed(16, 999), new PositionTolerance(-1));

        register("stormRider", new Configuration(config3, SafetyStatus.SAFE), new TimeLimit(Duration.ofHours(50)));

        Map<WindSpeed, PositionTolerance> config4 = new HashMap<>();
        config4.put(new WindSpeed(0, 2), new PositionTolerance(0.0));
        config4.put(new WindSpeed(2, 5), new PositionTolerance(0.15));
        config4.put(new WindSpeed(5, 9), new PositionTolerance(0.45));
        config4.put(new WindSpeed(9, 13), new PositionTolerance(0.75));
        config4.put(new WindSpeed(13, 999), new PositionTolerance(-1));

        register("windCatcher", new Configuration(config4, SafetyStatus.SAFE), new TimeLimit(Duration.ofHours(6)));

        Map<WindSpeed, PositionTolerance> config5 = new HashMap<>();
        config5.put(new WindSpeed(0, 4), new PositionTolerance(0.0));
        config5.put(new WindSpeed(4, 8), new PositionTolerance(0.3));
        config5.put(new WindSpeed(8, 12), new PositionTolerance(0.6));
        config5.put(new WindSpeed(12, 999), new PositionTolerance(-1));

        register("droneTypeA", new Configuration(config5, SafetyStatus.SAFE), new TimeLimit(Duration.ofHours(3)));

        return true;
    }

    private void register(final String modelNameStr, final Configuration config, final TimeLimit timeLimit) {
        try {
            ModelName modelName = new ModelName(modelNameStr);
            controller.createModel(modelName, config, timeLimit);
            LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully registered drone model: {}" + UtilsUI.RESET, modelNameStr);
        } catch (Exception e) {
            LOGGER.error(UtilsUI.BOLD + UtilsUI.RED + "Error registering drone model '{}': {}" + UtilsUI.RESET, modelNameStr, e.getMessage());
        }
    }
}
